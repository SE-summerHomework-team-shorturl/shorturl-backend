package com.example.userservice.dao.impl;


import com.example.sharedentity.entity.Shorturl;
import com.example.userservice.dao.ShorturlDao;
import com.example.userservice.repository.ShorturlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShorturlDaoImpl implements ShorturlDao {
    @Autowired private ShorturlRepository shorturlRepo;

    @Override
    public Shorturl findById(int id) {
        return shorturlRepo.findById(id).orElse(null);
    }

    @Override
    public List<Shorturl> findAllByUserId(int userId) {
        return shorturlRepo.findAllByUserId(userId);
    }

    @Override
    public Shorturl saveAndFlush(Shorturl shorturl) {
        return shorturlRepo.saveAndFlush(shorturl);
    }

    @Override
    public void deleteById(int id) {
        shorturlRepo.deleteById(id);
    }
}
