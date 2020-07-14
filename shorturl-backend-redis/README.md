# Short URL Backend (Redis)

## Redis存储方案

Redis中存储了以下这些key.

* `seq:user.id` (String类型)

    已生成的最大用户ID.
    
* `seq:short.url.id` (String类型)

    已生成的最大短链接ID.
    
* `username.index` (Hash类型)

    用户名到用户ID的一张哈希表.
   
    数据实例:
```
127.0.0.1:6381> HGETALL username.index
1) "bill"
2) "1"
3) "tom"
4) "2"
5) "jack"
6) "3"
```

* `user:${userId}` (Hash类型)

    ID为`userID`的用户的信息. Hash中, 一个键值对代表用户的一个属性.

    数据实例:
```
127.0.0.1:6380> HGETALL user:1
1) "password"
2) "1234"
3) "admin"
4) "false"
5) "email"
6) "bill@example.com"
7) "username"
8) "bill"
```
    
* `url:${shortUrlId}` (String类型)

    ID为`shortUrlId`的短链接所对应的长链接.

* `user.short.url.ids:${userID}` (Sorted set类型)
    
    ID为`userId`的用户所拥有的所有短链接的ID. Sorted set中, 每一个短链接ID的score等于这个ID本身.
    使用Sorted set而非Set是为了在查看短链接时实现分页.

    数据实例:
```
127.0.0.1:6380> ZRANGE user.short.url.ids:1 3 5 WITHSCORES
1) "4"
2) "4"
3) "10"
4) "10"
5) "11"
6) "11"
```
    
## Redis集群(Cluster模式)

该后端使用Redis集群(Cluster模式)作为数据源. 多台Redis主机构成一个集群, 每台主机有多台从机用于备份数据.
一般情况下, 从机不对外提供读写服务, 当一台主机出现故障时, 系统从其从机中选举出一台代替该主机.

使用Redis集群(Cluster模式), 可以实现水平扩容, 主从备份机制避免了数据库层面的单点故障.
