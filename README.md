

# 大二暑假大作业：短链接

## 项目成员：

- 陈阳
- 史嘉成
- 余庭瑞
- 太靖文

## 指导老师

- 任锐

# 项目需求

## 功能性需求

**重定向** 用户访问短链接时，被重定向至对应的长链接。

**登录与注册** 用户可以注册账号，并用注册时的账号密码登录。

**添加短链接 ** 用户提供一个合法URL后可以获得对应的短链接。

**查看短连接** 用户可以查看自己添加的短链接，以及该短链接在一个月内每天的点击量。管理员可以查看所有短链接。

**删除短连接** 用户可以删除自己的短链接，管理员可以删除任意短链接。

## 非功能性需求

1. **安全性** 不同用户的短链接（及其统计数据）应该是相互隔离的，只有对应用户登录后才能进行查看。为此我们使用了 基于JWT的OAuth2认证授权协议。
2. **可扩充性** 我们的短链接系统是可扩充的，因为其编码基于base62，故可以通过增加编码长度的方式来增加短链接的数量，其上限仅受到数据库的存储限制。
3. **可靠性** 基于Spring cloud微服务框架的并发机制，我们可以保证同一短链接在接收高并发请求时仍然保持功能完整，且每日点击量统计基本正确。

# 项目架构

![image.png](https://i.loli.net/2020/09/06/ZC3nWw5aQ6oBYtO.png)



上图为后端项目架构



## Consul服务注册中心

### 服务注册基本流程

本后端使用Consul进行微服务的发现与注册，微服务通过配置对应端口从而在consul上注册自己；每一个微服务在consul上的注册包括了服务名和实例名两部分，服务名相同的微服务会被自动分成一组。

据此，consul支持以服务名为单位设置访问策略，从而实现同一服务，不同实例之间的负载均衡。

其他服务则可以通过使用consul提供的dns、http接口查询，进行服务之间的通讯。

同时，consul还得以依据服务名，对微服务进行健康检查和监测，并将监测数据暴露给prometheus等自动监控系统

最终生成的服务列表如下图：

![image.png](https://i.loli.net/2020/09/06/Aq8nPUy6eOzXVfr.png)

### 服务注册中心实现概要

与eureka不同，Consul需要独立于项目运行；而在一个spring boot项目中引入如下依赖，就可将其转变为一个可以被consul发现的微服务。

```xml
 <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
 </dependency>
```

同时在该项目的配置文件中配置与Consul对应的端口和注册名，就可将该服务以如下的服务名和实例名注册到Consul中。

```yml
spring:
  cloud:
    consul:
      host: consul
      port: 8500
      discovery:
        instance-id: ${spring.application.name}:${spring.application.instance_id:${random.value}}
```



## OAuth2认证与授权

### 认证与授权流程
本后端采用基于JWT的OAuth2认证与授权协议. 在认证流程中, 包含三个角色: 用户, 认证中心与资源服务器. 认证中心负责access_token的签发与验证. 资源服务器提供一些需要认证与授权的服务. 在本后端中, 资源服务器包括addurl-service实例, userurl-service实例与admin-service实例.

认证中心持有一个公私钥对. 认证中心签发的access_token的格式为JWT (JSON Web Token). 一个JWT字符串包含Header, Payload与Signature三段, 其中Payload段为负载, 携带着用户ID与权限, Signature段为数字签名, 用私钥加密.

本后端的认证与授权流程如下图所示.
![oauth2.png](https://i.loli.net/2020/08/27/MISAC1rj4hQFsL9.png)

1. 用户向认证中心提供用户名和密码.
2. 认证中心在校验用户名与密码无误后, 向用户签发access_token.
3. 用户向资源服务器发出请求, 访问某个需要认证与授权的服务, 在请求头中携带有access_token.
4. 资源服务器受到用户的请求后, 为了要验证用户发来的access_token, 向认证中心索取公钥.
5. 认证中心向资源服务器返回公钥.
6. 资源服务器用公钥成功解密access_token的Signature段并验证Signature段无误后, 根据access_token的Payload段中的用户ID与权限来处理用户的请求, 并向用户返回响应.

### 认证中心实现概要
在一个Spring Boot工程中引入以下依赖, 并进行一系列配置之后, 即得到一个基于JWT的OAuth2认证中心.
```XML
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-oauth2</artifactId>
</dependency>
```

认证中心有两个关键接口, 一个是`/oauth/token`, 用于获取access_token, 另一个是`/.well-known/jwks.json`, 用于获取公钥.

相关文档: https://docs.spring.io/spring-security-oauth2-boot/docs/current/reference/html5/#oauth2-boot-authorization-server-spring-security-oauth2-resource-server.

### 资源服务器实现概要
在一个Spring Boot工程中引入以下依赖, 并进行一系列配置之后, 即得到一个基于JWT的OAuth2资源服务器.
```XML
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>
```

需要在资源服务器中配置获取公钥所用的URI.
```properties
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=http://localhost:8080/.well-known/jwks.json
```

在Controller类的某个方法上加上如下注解, 可以限制该方法只能由管理员访问.
```Java
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
```

在Service类的某个方法中, 可以用如下方法获取到访问当前服务的用户的ID.
```Java
long userId = Long.parseLong((String) ((Jwt) SecurityContextHolder.getContext()
		.getAuthentication().getCredentials()).getClaims().get("user_name"));
```

相关文档: https://docs.spring.io/spring-security/site/docs/current/reference/html5/#oauth2resourceserver.

## RabbitMQ消息队列

### 队列通讯流程

本后端存在一些并发量较大、消耗较大、但是对一致性要求不高的服务（如短链接访问的统计功能），使用RabbitMQ消息队列进行异步处理。

RabbitMQ基于AMQP协议，将消息队列拆分成生产者、信道、消费者三个部分；其中 RabbitMQ本身作为信道，负责消息的存储和交换。

生产者是投递消息的一方，首先连接到Server，建立一个连接，开启一个信道；然后生产者声明交换器和队列，设置相关属性，并通过路由键将交换器和队列进行绑定。同理，消费者也需要进行建立连接，开启信道等操作，便于接收消息。

接着生产者就可以发送消息，发送到服务端中的虚拟主机，虚拟主机中的交换器根据路由键选择路由规则，然后发送到不同的消息队列中，这样订阅了消息队列的消费者就可以获取到消息，进行消费。其结构如下图所示：

![image.png](https://i.loli.net/2020/09/06/x4kWO9l7zBZywUH.png)

基于如上机制，RabbitMQ实现了消息生产和消费的解耦，同时还可以实现消息的分类处理与消费者的负载均衡。

### 消息队列实现概要

在一个spring boot 项目中引入如下依赖，即可在项目中使用RabbitMQ：

```xml
 <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
  </dependency>
```

在生产者项目中实现如下消息生产者，即可通过调用该方法发送信息：

```java
@Service
public interface ISendeService {
    @Output("dpb-exchange")
    SubscribableChannel send();
}

```

同理，在消费者项目中实现对应的消息消费者，并与生产者连接同一信道，即可实现消费者和生产者的连接：

```java
public interface IReceiverService {
    @Input("dpb-exchange")
    SubscribableChannel receiver();
}
```

## 自动监控数据可视化

	### 基于prometheus的自动监控

Consul服务注册中心自带对已经注册的微服务进行监测和健康检查功能，故只需要将对应数据的接口暴露给prometheus即可实现自动监控：

在需要监控的spring boot项目中添加如下依赖：

~~~xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>

~~~

同时进行如下配置：

~~~yml
management.endpoints.web.exposure.include="*"
~~~

即可在对应的`/actuator/prometheus`接口中看到监控数据如下图所示：

![image.png](https://i.loli.net/2020/09/06/qX5fUPwdZHn2WJs.png)

### 基于grafana的监控数据可视化 

grafana是一个跨平台的开源的度量分析和可视化工具。基于prometheus所提供的微服务监控数据，grafana可以将采集的数据查询并进行可视化处理，并提供了自定义的警告通知功能。

## Module列表

| service           | 内容         | 端口  |
| :---------------- | :----------- | :---- |
| shared-entity     | 共享基本类   |       |
| misc              | 工具类       |       |
| config-server     | 配置中心     | :8888 |
| gateway           | 网关         | :8080 |
| auth-server       | 权限认证中心 | :8000 |
| redirect-service  | 重定向服务   | :8001 |
| register-service  | 注册服务     | :8031 |
| userurl-service   | 查询url服务  | :8021 |
| addurl-service    | url添加服务  | :8011 |
| admin-service     | 业务管理服务 | :8041 |
| statistic-service | 数据统计服务 | :8051 |

## 工具类

### shared-entity

存储各服务通用的基本类：

- 实现了连接数据库的repository、dao层和对应实体类
- 实现了Base62加密解密代码
- 实现了与前端通讯的通用Message类和DTO类

### misc

存储不需要连接数据库的工具类：

- 实现了用于用户认证的userDetail类

## 配置中心

### config-server

基于Spring Cloud Config 的配置中心

与git上对应的repository进行通信，对所有service进行配置管理

## 权限认证中心

### auth-server

基于JWT的OAuth2认证与授权协议实现的权限认证中心

为其他微服务提供注册、登录、权限认证

## 网关

### gateway

基于 spring cloud gateway 的网关

- 对前端暴露URL接口，并进行统一路由
- 实现了负载均衡

## 微服务

### redirect-service

```json
> /r/{token}

> POST

> 返回重定向网页
```

### register-service

 ```json
> /user/register

> POST
{
email : test@test.com
username : user1
password : 123456
 }

> MESSAGE:
   {
	status:"SUCCESS"
 	body:NULL
 }
 ```


### addurl-service

 ```json
 > /urlmanage/addurl
 
 > GET 需要jwt验证
 {
	url : "www.baidu.com"
 }

 > MESSAGE:
   {
	status:"SUCCESS"
 	body:{
      id: 1
      url: "www.baidu.com"
      userId: 1
      shortUrlStat: null
      token: "1"
	 }
 }

 ```
### admin-service

 ```json
 > /admin/finduser
 
 > GET 需要jwt验证

 > MESSAGE:
   {
	status:"SUCCESS"
 	body:[{
      username: user1
      password: dfhjdtujhbj
      email:test@test.com
      admin: 1
	 }]
 }
 ```

 ```json

 > /admin/findurl
 
 > GET 需要jwt验证

 > MESSAGE:
  {
	status:"SUCCESS"
 	body:[{
      id: 1
      url: "www.baidu.com"
      userId: 1
      shortUrlStat: null
	 }]
 }
 ```

 ```json
 > /admin/deleteurl
 
 > GET 需要jwt验证
  {
	id: 1
 }

 > MESSAGE:
  {
	status:"SUCCESS"
 	body:null
 }
 ```


### userurl-service

 ```json

 > /urlmanage/findurl
 
 > GET 需要jwt验证

 > MESSAGE:
  {
	status:"SUCCESS"
 	body:[{
      id: 1
      url: "www.baidu.com"
      userId: 1
      shortUrlStat: null
	 }]
 }
 ```


 ```json
 > /urlmanage/deleteurl
 
 > GET 需要jwt验证

 > MESSAGE:
 {
	status:"SUCCESS"
 	body: null
 }
 ```
# 项目性能测试

**测试工具**: JMeter 5.3

**部署方式**: 所有组件部署在同一台服务器上.

**服务器参数**:

| 参数     | 值                              |
| -------- | ------------------------------- |
| 处理器   | Intel Core i7-8550U             |
| 内存大小 | 8.00GB                          |
| 硬盘     | KXG50ZNV256G NVMe TOSHIBA 256GB |

### 重定向服务响应时间

**测试内容**: 不同并发量下, 重定向服务的响应时间.

**测试方法**: 在不同的的并发量情况下, 测试重定向服务, 每次测试时, 记录响应时间的中位数, 平均数, 90%分位点与95%分位点. 每次发送请求时, 随机选择一个数据库中的短链接作为目标. JMeter设置不跟随重定向.

**实例个数**: redirect-service部署1个实例.

**数据量**: 数据库中预先存入100000条短链接.

**循环次数**: 每个线程循环200次.

**结果**:
![redirect-concurrency.png](https://i.loli.net/2020/08/27/hHc2WQJX3NKordR.png)

**分析**: 在400并发, 数据库中存有100000条短链接的情况下, 重定向服务响应时间的95%分位点在400ms以内.

### 添加短链接服务响应时间

**测试内容**: 不同并发量下, 添加短链接服务的响应时间.

**测试方法**: 在不同的的并发量情况下, 测试添加短链接服务, 每次测试时, 记录响应时间的平均数, 90%分位点与95%分位点.

**实例个数**: addurl-service部署1个实例.

**数据量**: 每次测试开始前, 清除数据库中的所有短链接.

**循环次数**: 每个线程循环200次.

**结果**:
![addurl-concurrency.png](https://i.loli.net/2020/08/27/r64vMbnlH71ZDmB.png)

**分析**: 在400并发的情况下, 重定向服务响应时间的95%分位点在1600ms以内.

### 网关与点击量统计对重定向服务性能的影响

**测试内容**: 研究去除网关或点击量统计的情况下, 重定向服务的响应时间的变化.

**测试方法**:  测试普通, 无网关, 无点击量统计三种情况下, 重定向服务的响应时间的90%分位点关于并发量的变化. 无网关模式下, 请求直接发送给redirect-service实例(只部署1个实例). 无点击量统计情况下, 重定向服务流程中不向RabbitMQ添加redirect record.

**实例个数**: redirect-service部署1个实例.

**数据量**: 数据库中预先存入100000条短链接.

**循环次数**: 每个线程循环200次.

**结果**:
![redirect-cases.png](https://i.loli.net/2020/08/27/O9uihVFAQ2UCkaZ.png)

**分析**: 去除网关或点击量统计会使得重定向服务响应时间变短, 但变短的幅度都不大, 可以认为网关与点击量统计对于重定向服务的性能没有太大的影响.

### 微服务实例数目对重定向服务性能的影响(单台服务器)

**测试内容**: 不同redirect-service实例数目下, 重定向服务的响应时间. 所有redirect-service实例部署在一台服务器上.

**测试方法**: 在不同的redirect-service实例数目下, 测试重定向服务的响应时间的90%分位点关于并发量的变化. 所有redirect-service实例部署在一台服务器上.

**数据量**: 数据库中预先存入100000条短链接.

**循环次数**: 每个线程循环200次.

**结果**:
![redirect-single-machine-multi-inst.png](https://i.loli.net/2020/08/27/EibvyH3swd1XmOu.png)

**分析**: 在一台服务器上部署多个redirect-service实例, 部署的实例数增加, 重定向服务的响应时间增长. 这可能是由于实例数增加时, 服务器的硬件资源消耗增加所致的. 要通过增加实例数目提高重定向服务的性能, 需要将各个实例部署在不同的服务器上.



