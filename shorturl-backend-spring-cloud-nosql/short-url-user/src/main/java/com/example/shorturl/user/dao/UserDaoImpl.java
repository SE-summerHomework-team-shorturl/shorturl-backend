package com.example.shorturl.user.dao;

import com.example.shorturl.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User findByUsernameOrCreate(User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(user.getUsername()));

        Update update = new Update();
        update.setOnInsert("_id", user.getId());
        update.setOnInsert("username", user.getUsername());
        update.setOnInsert("password", user.getPassword());
        update.setOnInsert("email", user.getEmail());
        update.setOnInsert("admin", user.getAdmin());

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        options.upsert(true);

        return mongoTemplate.findAndModify(query, update, options, User.class);
    }
}
