import random
import base64
import pymongo

def calc_basic_token(username, password):
    return base64 \
            .b64encode('{}:{}'.format(username, password).encode('UTF-8')) \
            .decode('ASCII')

class Data:
    def __init__(self, db):
        db.users.drop()
        db.short_urls.drop()

        self._generate_users(100000)
        result = db.users.insert_many(self.users)
        for i in range(len(self.users)):
            self.users[i]['_id'] = result.inserted_ids[i]

        self._generate_short_urls(62 ** 4)
        result = db.short_urls.insert_many(self.short_urls)
        for i in range(len(self.short_urls)):
            self.short_urls[i]['_id'] = result.inserted_ids[i]

    def _generate_users(self, size):
        self.users = [
            {
                'username': str(i),
                'password': str(i),
                'email': '{}@example.com'.format(i),
                'admin': False,
            }
            for i in range(size)
        ]

    def _generate_short_urls(self, size):
        self.short_urls = [
            {
                'url': 'http://www.example.com',
                'userId': random.choice(self.users)['_id'],
            }
            for i in range(size)
        ]

if __name__ == '__main__':
    client = pymongo.MongoClient('mongodb://localhost:27017/')
    db = client.short_url_db

    data = Data(db)

    tokens_file = open('tokens.csv', 'w')
    usernames = [user['username'] for user in data.users]
    passwords = [user['password'] for user in data.users]
    tokens = list(map(calc_basic_token, usernames, passwords))
    tokens_file.write('\n'.join(tokens))
    tokens_file.close()
