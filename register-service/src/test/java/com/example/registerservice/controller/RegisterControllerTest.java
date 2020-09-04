package com.example.registerservice.controller;

import com.example.registerservice.service.RegisterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class RegisterControllerTest {
    @Autowired
    private MockMvc mockMvc;
    //@MockBean
    //private RegisterService registerService;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("shouldSuccessWhenRightUserMessage")
    void register() throws Exception {
        String testEmail="testEmail@sjtu.edu.cn";
        String testName="testname";
        String testPassword="123456";
        String requestBody =  "{\r\n    \"email\":\"test@sjtu.edu.cn\",\r\n    \"username\":\"testname\",\r\n    \"password\":\"123456\"\r\n}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())

                .andDo(MockMvcResultHandlers.print())

                .andReturn();
        System.out.println(result);

    }
}
