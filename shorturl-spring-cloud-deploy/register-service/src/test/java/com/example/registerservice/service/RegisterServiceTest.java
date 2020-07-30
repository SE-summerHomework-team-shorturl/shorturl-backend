package com.example.registerservice.service;

import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class RegisterServiceTest {
    @Autowired
    private RegisterService  registerService;

    private Map<String,String> map = new HashMap<String, String>();
    private User user = new User();
    @BeforeEach
    void setUp() {
        map.put("email","testEmail@sjtu.edu.cn");
        map.put("username","testname1111111");
        map.put("password","123456");
    }

    @AfterEach
    void tearDown() {
    }



    @Test
    @Order(1)
    @DisplayName("shouldSuccessWhenFirstRegister")
    void registerTest1() {
        Message firstMessage= registerService.register(map);
        assertEquals(Message.Success_Msg,firstMessage.getStatus());

    }
    @Test
    @Order(2)
    @DisplayName("shouldDupWhenDupRegister")
    void registerTest2() {
        Message firstMessage= registerService.register(map);
        assertEquals(Message.Success_Msg,firstMessage.getStatus());
        Message DupMessage= registerService.register(map);
        assertEquals(Message.Dup_User_Msg,DupMessage.getStatus());
    }
}
