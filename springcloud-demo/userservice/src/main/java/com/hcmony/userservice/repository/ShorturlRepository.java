package com.hcmony.userservice.repository;


import com.example.sharedentity.entity.Shorturl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShorturlRepository extends JpaRepository<Shorturl, Integer> {
    List<Shorturl> findAllByUserId(Integer userId);
}
