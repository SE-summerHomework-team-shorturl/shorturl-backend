# Shorturl Backend (Naive)

## 数据库

数据库建表脚本为`/src/main/resources/static/shorturl.sql`.

## Message

除了重定向服务外, 其他服务若无异常则在response body中返回一个Message对象, 包含status与body两个字段.

## 用户认证

需要用户认证的服务需要在request header中添加basic token, 具体方法如下.

若用户名为`bill`, 密码为`1234`, 将用户名与密码用冒号连缀得到`bill:1234`, 对这个字符串做base64转码,
得到`YmlsbDoxMjM0`, 在request header中添加`Authorization`字段, 其值为`Basic YmlsbDoxMjM0`.

访问需要用户认证的服务, 但未提供正确的basic token, response status code为401 (Unauthorized).

## 不需要用户认证的服务

* `/r/{token}`

该短链接重定向到参数`token`对应的URL, 若参数`token`没有对应的URL, 会打印错误信息.

* `/user/register` (`POST`)

注册用户, request body为用户信息, 格式如下.
```json
{
  "username": "bill",
  "password": "1234",
  "email": "bill@example.com"
}
```

若用户名已存在, 返回的`status`为`DUP_USERNAME`, 否则返回的`status`为`SUCCESS`. 注册得到的用户都是非管理员.

## 需要用户认证的服务

* `/login` (`GET`)

登录. 返回的`status`为`SUCCESS`, 返回的`body`为用户的信息, 格式如下.
```json
{
    "id": 1,
    "username": "bill",
    "email": "bill@example.com",
    "admin": false
}
```

* `/shorturl/add-to-my-shorturls?url={url}` (`GET`)

添加短链接. 参数`url`为要生成短链接的URL.

若输入的URL不合法, 则返回的`status`为`INVALID_URL`. 否则, 返回的`status`为`SUCCESS`,
`body`为生成的短链接对象, 格式如下.
```json
{
    "id": 79,
    "url": "https://www.baidu.com",
    "userId": 1,
    "token": "1H"
}
```

* `/shorturl/find-all-my-shorturls` (`GET`)

查找本用户的所有短链接, 返回的`status`为`SUCCESS`, `body`为本用户所有的短链接对象,
格式如下.

```json
[
    {
        "id": 1,
        "url": "https://www.baidu.com",
        "userId": 1,
        "token": "1"
    },
    {
        "id": 2,
        "url": "https://www.baidu.com",
        "userId": 1,
        "token": "2"
    },
    {
        "id": 3,
        "url": "https://www.baidu.com",
        "userId": 1,
        "token": "3"
    }
]
```

* `/shorturl/delete-my-shorturl-by-id?id={id}` (`GET`)

删除本用户的短链接. 参数`id`为要删除的短链接的ID.

若删除成功或要删除的短链接不存在, 返回的`status`为`SUCCESS`, 若参数`id`指定的短链接不是本用户的,
返回的`status`为`NOT_YOUR_SHORTURL`.
