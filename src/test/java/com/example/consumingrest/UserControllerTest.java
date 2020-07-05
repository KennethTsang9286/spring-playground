package com.example.consumingrest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHome() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(equalTo(
                        "<!DOCTYPE html>\n<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:sec=\"https://www.thymeleaf.org/thymeleaf-extras-springsecurity3\">\n\n<head>\n    <title>Spring Security Example</title>\n</head>\n\n<body>\n    <h1>Welcome!</h1>\n\n    <p>Click <a href=\"/helloWorld\">here</a> to see a greeting.</p>\n</body>\n\n</html>")));
    }
}