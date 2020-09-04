# 后端架构与设计
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
