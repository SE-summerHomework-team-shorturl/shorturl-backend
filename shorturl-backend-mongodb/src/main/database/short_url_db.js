let conn = new Mongo('localhost:27017');
let db = conn.getDB('short_url_db');

db.users.drop();
db.short_urls.drop();
db.counters.drop();

db.createCollection('users');
db.createCollection('short_urls');
db.createCollection('counters');

db.counters.insert({ _id: 1, seq: 1 });
db.counters.insert({ _id: 2, seq: 1 });
