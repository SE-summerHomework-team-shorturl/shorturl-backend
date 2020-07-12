redis.call('FLUSHDB')
redis.call('SET', 'seq:user.id', '0')
redis.call('SET', 'seq:short.url.id', '0')
