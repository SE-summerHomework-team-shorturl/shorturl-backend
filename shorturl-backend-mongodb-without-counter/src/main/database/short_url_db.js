let conn = new Mongo('localhost:27017');
let db = conn.getDB('short_url_db');
db.dropDatabase();
db = conn.getDB('short_url_db');

db.createCollection('users');
db.createCollection('short_urls');

db.users.createIndex({ username: 1 }, { unique: true });
