package com.example.redirectservice.controller;

import com.example.redirectservice.service.RedirectService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
@Profile("test")
@SpringBootTest
class RedirectControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RedirectController redirectController;
    @MockBean
    private RedirectService redirectService;

    @BeforeEach
    void setUp() {
        System.out.println("setUp");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
    }
    @Test
    void redirect() throws Exception {
        String token="2";
        redirectController.print();
     //   verify(redirectController, times(1)).print();
    //    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/r/2"))
     //           .andDo(MockMvcResultHandlers.print())
       //         .andExpect(MockMvcResultMatchers.status().is4xxClientError())
//
       //         .andExpect(MockMvcResultMatchers.jsonPath("$.errors").exists())
       ///         .andReturn();
    }
}
