package com.example.redirectservice.controller;

import com.example.redirectservice.service.RedirectService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@AutoConfigureMockMvc
@SpringBootTest
class RedirectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        System.out.println("setUp");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
    }
    @Test
    @DisplayName("shouldSuccessWhenRightRequest")
    void test1() throws Exception {

       MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/r/2"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.content().string("http://www.baidu.com"))
               .andReturn();
    }
    @Test
    @DisplayName("shouldGet500WhenWrongRequest")
    void test2() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/r/0"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }
}
