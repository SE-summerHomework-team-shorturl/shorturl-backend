import random
import base64

def table_to_sql(table, schema):
    size = len(table)
    command = 'INSERT INTO {} VALUES\n'.format(schema)
    sql = ''
    for i in range(0, size, 100000):
        data = ',\n'.join(
            [
                str(row) if len(row) != 1 else '({})'.format(repr(row[0]))
                for row in table[i:i + 100000]
            ]
        )
        sql += command + data + ';\n'
    return sql

def calc_basic_token(username, password):
    return base64 \
            .b64encode('{}:{}'.format(username, password).encode('UTF-8')) \
            .decode('ASCII')

class Database:
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
                str(i), # username
                str(i), # password
                '{}@example.com'.format(i), # email
                False, # admin
            )
            for i in range(size)
        ]

    def _generate_short_urls(self, size):
        users_size = len(self.users)
        self.short_urls = [
            (
                'http://www.example.com', # url
                random.randint(1, users_size), # user
            )
            for i in range(size)
        ]

if __name__ == '__main__':
    db = Database()
    
    insert_file = open('insert.sql', 'w')
    insert_file.write(db.to_sql())
    insert_file.close()

    tokens_file = open('tokens.csv', 'w')
    usernames = [user[0] for user in db.users]
    passwords = [user[1] for user in db.users]
    tokens = list(map(calc_basic_token, usernames, passwords))
    tokens_file.write('\n'.join(tokens))
    tokens_file.close()
