# 后端部署方案

后端部署在一个Docker Swarm集群上. Docker Swarm集群是由多台Docker主机搭建而成的集群. 在一个Docker Swarm集群上可以部署一系列服务, 每个服务的配置包括所用镜像, 实例数目, 端口映射等等, Docker Swarm系统会在整个集群中维护这些服务.

利用docker-compose.yml文件, 可以定义一个包含一组服务的服务栈. 我们利用以下语句在一个Docker Swarm集群中部署或更新短链接服务栈.
```bash
docker stack up -c docker-compose.yml shorturl
```
服务栈中的容器, 可以通过服务名访问该服务栈中的其他服务, 例如通过consul:8500访问Consul服务.

对于数据库服务(MySQL, Redis与MongoDB), 我们采用Docker Swarm的卷(Volume)挂载机制来使得数据被持久化. 通过添加容器编排相关的限制(deploy.placement.contraints), 我们确保MySQL容器始终部署在特定的Docker主机上, 以确保它始终挂载的是同一个卷(对于Redis和MongoDB也是这样). 以下是MongoDB服务在docker-compose.yml中的定义.
```YAML
mongo:
  image: mongo:latest
  volumes:
    - mongo-data:/data/db
  deploy:
    placement:
      constraints:
        - node.labels.mongo==true
```

对于Gateway服务, 我们使用Docker Swarm的端口映射机制来使得其能在短链接服务栈外部被访问. 进行端口映射之后, 可以通过Docker Swarm集群中任意一台主机的相应端口来访问Gateway服务. 以下是Gateway服务在docker-compose.yml中的定义.
```YAML
gateway:
  build: ./gateway
  image: 127.0.0.1:5000/shorturl/gateway:latest
  ports:
    - 8080:8080
```

对于需要部署多个实例的服务, 只需在docker-compose.yml文件中配置deploy.replicas即可. 以下是重定向服务在docker-compose.yml中的定义.
```YAML
redirect-service:
  build: ./redirect-service
  image: 127.0.0.1:5000/shorturl/redirect-service:latest
  deploy:
    replicas: 2
```

下图显示了短链接服务栈部署完毕后, Docker Swarm集群中服务的状态.
![services.PNG](https://i.loli.net/2020/09/06/uJDtbiWj1x6go9f.png)
