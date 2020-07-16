# shorturl-backend
大二暑假大作业：短链接（后端）
# spring cloud 版本
将所有service 作为微服务嵌入 spring cloud

## module列表：

	eureka：服务注册中心
	shared-entity：共享基本类（**未做单元测试**）
	config-server：配置中心
	config-client：配置客户端（调试用，以废弃）
	security-gateway：网关（已弃用）
	redirect-service：重定向服务
	register-service：注册服务
	userurl-service：查询url服务（**未做单元测试**）
	addurl-service：url添加服务
	login-service：注册服务
	consumer：负载均衡（已弃用）
