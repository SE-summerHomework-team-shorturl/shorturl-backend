package com.example.registerservice.service;

import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class RegisterServiceTest {
    @Autowired
    private RegisterService  registerService;

    private User user = new User();
    @BeforeEach
    void setUp() {
        String testEmail = "testEmail@sjtu.edu.cn";
        user.setEmail(testEmail);
        String testName = "testname1111111";
        user.setUsername(testName);
        String testPassword = "123456";
        user.setPassword(testPassword);
    }

    @AfterEach
    void tearDown() {
    }



    @Test
    @Order(1)
    @DisplayName("shouldSuccessWhenFirstRegister")
    void registerTest1() {
        Message firstMessage= registerService.register(user);
        assertEquals("SUCCESS",firstMessage.getStatus());

    }
    @Test
    @Order(2)
    @DisplayName("shouldDupWhenDupRegister")
    void registerTest2() {
        Message firstMessage= registerService.register(user);
        assertEquals("SUCCESS",firstMessage.getStatus());
        Message DupMessage= registerService.register(user);
        assertEquals("DUP_USERNAME",DupMessage.getStatus());
    }
}
