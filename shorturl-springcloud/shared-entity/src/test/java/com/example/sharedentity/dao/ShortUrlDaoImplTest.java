package com.example.sharedentity.dao;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ShortUrlDaoImplTest {

    // TODO test both shortUrl and urlStatistic table after add
    @Test
    void addAndFlush() {
    }
}
