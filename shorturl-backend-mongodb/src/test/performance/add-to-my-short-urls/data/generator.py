import random
import base64
import pymongo

def calc_basic_token(username, password):
    return base64 \
            .b64encode('{}:{}'.format(username, password).encode('UTF-8')) \
            .decode('ASCII')

class Data:
    def __init__(self):
        self._generate_users(100000)
        self._generate_short_urls(62 ** 4 - 1)
        self._generate_counters()

    def _generate_users(self, size):
        self.users = [
            {
                '_id': id,
                'username': str(id),
                'password': str(id),
                'email': '{}@example.com'.format(id),
                'admin': False,
            }
            for id in range(1, size + 1)
        ]

    def _generate_short_urls(self, size):
        users_size = len(self.users)
        self.short_urls = [
            {
                '_id': id,
                'url': 'http://www.example.com',
                'userId': random.randint(1, users_size),
            }
            for id in range(1, size + 1)
        ]

    def _generate_counters(self):
        users_size = len(self.users)
        short_urls_size = len(self.short_urls)
        self.counters = [
            { '_id': 1, 'seq': users_size + 1 },
            { '_id': 2, 'seq': short_urls_size + 1 },
        ]

if __name__ == '__main__':
    data = Data()
    
    client = pymongo.MongoClient('mongodb://localhost:27017/')
    db = client.short_url_db
    db.users.drop()
    db.short_urls.drop()
    db.counters.drop()
    db.users.insert_many(data.users)
    db.short_urls.insert_many(data.short_urls)
    db.counters.insert_many(data.counters)

    tokens_file = open('tokens.csv', 'w')
    usernames = [user['username'] for user in data.users]
    passwords = [user['password'] for user in data.users]
    tokens = list(map(calc_basic_token, usernames, passwords))
    tokens_file.write('\n'.join(tokens))
    tokens_file.close()
