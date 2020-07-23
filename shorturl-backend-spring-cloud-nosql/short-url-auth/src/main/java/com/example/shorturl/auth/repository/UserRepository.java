package com.example.shorturl.auth.repository;

import com.example.shorturl.auth.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findOneByUsername(String username);
}
