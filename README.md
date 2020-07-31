# shorturl-backend

大二暑假大作业：短链接（后端）

## spring cloud 版本

将所有service 作为微服务嵌入 spring cloud

## module列表
service | 内容 | 端口
---|:--:|---:
shared-entity | 共享基本类 |  
misc|工具类|
config-server|配置中心|:8888
gateway | 网关 |:8080
auth-server|权限认证中心|:8000
redirect-service|重定向服务|:8001
register-service|注册服务|:8031
userurl-service|查询url服务|:8021
addurl-service|url添加服务|:8011
admin-service|业务管理服务|:8041
statistic-service | 数据统计服务 |:8051

---

* 服务中心：consul
  
  默认端口:8500
  >consul agent -dev

* 消息队列: RabbitMQ

  默认端口:15672

* 自动监控：prometheus

  默认端口:9090

* 数据可视化：grafana

  默认端口:3000

---

对应sql和postman测试脚本：
>\docs 