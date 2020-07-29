import random


def table_to_sql(table, schema):
    size = len(table)
    command = 'INSERT INTO {} VALUES\n'.format(schema)
    sql = ''
    for i in range(0, size, 100000):
        values = ',\n'.join(
            [
                str(row) if len(row) != 1 else '({})'.format(repr(row[0]))
                for row in table[i:i + 100000]
            ]
        )
        sql += command + values + ';\n'
    return sql


def base62_encode(num):
    digits = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'
    token = ''
    while num > 0:
        digit = digits[num % 62]
        token = digit + token
        num //= 62
    return token if len(token) else '0'


class Data:
    def __init__(self):
        self._generate_users(100000)
        self._generate_short_urls(62 ** 4 - 1)

    def to_sql(self):
        return '\n'.join([
            table_to_sql(self.users, '`users`(`username`, `password`, `email`, `admin`)'),
            table_to_sql(self.short_urls, '`short_urls`(`url`, `user`)'),
        ])

    def _generate_users(self, size):
        self.users = [
            (
                str(i),  # username
                str(i),  # password
                '{}@example.com'.format(i),  # email
                False,  # admin
            )
            for i in range(size)
        ]

    def _generate_short_urls(self, size):
        users_size = len(self.users)
        self.short_urls = [
            (
                'http://www.example.com',  # url
                random.randint(1, users_size),  # user
            )
            for i in range(size)
        ]


if __name__ == '__main__':
    data = Data()

    insert_file = open('insert.sql', 'w')
    insert_file.write(data.to_sql())
    insert_file.close()

    token_file = open('tokens.csv', 'w')
    token_file.write('\n'.join(map(base62_encode, range(1, len(data.short_urls) + 1))))
    token_file.close()
