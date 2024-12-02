package com.ercin.user.todoapp.controller;

import com.ercin.user.todoapp.dto.CreateUserRequestDto;
import com.ercin.user.todoapp.service.TodoListService;
import com.ercin.user.todoapp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private TodoListService todoListService;


    @Test
    void createUserTests() throws Exception {
        String req = new ObjectMapper().writeValueAsString(createUserRequestDto());
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/admin/createUser")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(req)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError());

        Mockito.verify(this.userService, Mockito.times(0)).createUser(any());
    }

    private CreateUserRequestDto createUserRequestDto() {
        CreateUserRequestDto requestDto = new CreateUserRequestDto();
        requestDto.setEmail("test");
        requestDto.setPhone("test");
        requestDto.setFirstName("test2");
        requestDto.setConfirmPassword("test");
        requestDto.setLastName("test1");
        requestDto.setPassword("test");
        return requestDto;
    }
}
