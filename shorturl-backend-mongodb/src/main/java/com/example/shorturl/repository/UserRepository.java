package com.example.shorturl.repository;

import com.example.shorturl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Integer> {
    boolean existsByUsername(String username);
    Optional<User> findOneByUsername(String username);
}
