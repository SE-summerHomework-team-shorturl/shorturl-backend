package com.example.sharedentity.dao;


import com.example.sharedentity.repository.UserRepository;
import com.example.sharedentity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = {"users"})
public class UserDaoImpl implements UserDao {
    @Autowired private UserRepository userRepo;

    @Override
    @Cacheable(key="#username")
    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    @Cacheable(key="#username")
    public User findOneByUsername(String username) {
        return userRepo.findOneByUsername(username).orElse(null);
    }

    @Override
    @CachePut(key="#user.getUsername()")
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
}
