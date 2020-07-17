package com.example.userurlservice.controller;

import com.example.sharedentity.dto.Message;
import com.example.userurlservice.service.UserUrlService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UserUrlControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserUrlService userUrlService;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("shouldSuccessWhenRightInput")
    void findAllMyShortUrls() throws Exception {
        int page=1;
        int size=1;
        int userId=1;
        String testInfo="test info";
        when( userUrlService.findAllMyShortUrls(page,size)).thenReturn(new Message(testInfo,null));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/urlmanage/findurl")
                .param("page",String.valueOf(page))
                .param("size",String.valueOf(size))
                .param("userId",String.valueOf(userId))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value(testInfo))
                .andDo(MockMvcResultHandlers.print())

                .andReturn();
      //  System.out.println(result);
    }

    @Test
    @DisplayName("shouldSuccessWhenRightInput")
    void deleteMyShortUrlById() throws Exception {

        int id=1;
        int userId=1;
        String testInfo="test info";
        when( userUrlService.deleteMyShortUrlById(id)).thenReturn(new Message(testInfo,null));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/urlmanage/deleteurl")
                .param("id",String.valueOf(id))
                .param("userId",String.valueOf(userId))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value(testInfo))
                .andDo(MockMvcResultHandlers.print())

                .andReturn();
    }
}
