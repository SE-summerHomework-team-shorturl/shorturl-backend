package com.example.shorturl.dao.impl;

import com.example.shorturl.dao.ShorturlDao;
import com.example.shorturl.entity.Shorturl;
import com.example.shorturl.repository.ShorturlRepository;
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
