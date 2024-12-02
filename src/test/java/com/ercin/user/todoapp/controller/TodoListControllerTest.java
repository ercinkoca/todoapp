package com.ercin.user.todoapp.controller;

import com.ercin.user.todoapp.dto.CreateTodoListRequestDto;
import com.ercin.user.todoapp.service.TodoListService;
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
class TodoListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TodoListService todoListService;


    @Test
    void createTodoListTests() throws Exception {
        String req = new ObjectMapper().writeValueAsString(createTodoListRequest());
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/todoList/createTodoList")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(req)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Mockito.verify(this.todoListService, Mockito.times(0)).createTodoList(any());
    }

    @Test
    void getTodoListTests() throws Exception {
        String req = new ObjectMapper().writeValueAsString(createTodoListRequest());
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/todoList/getTodoList")
                        .param("todoListName","example")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Mockito.verify(this.todoListService, Mockito.times(0)).getTodoList(any());
    }

    @Test
    void updateTodoListTests() throws Exception {
        String req = new ObjectMapper().writeValueAsString(createTodoListRequest());
        this.mockMvc.perform(
                MockMvcRequestBuilders.put("/todoList/updateTodoList")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(req)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Mockito.verify(this.todoListService, Mockito.times(0)).updateTodoList(any());
    }

    @Test
    void getTodoListByUserEmailTests() throws Exception {
        String req = new ObjectMapper().writeValueAsString(createTodoListRequest());
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/todoList/getTodoListByUserEmail")
                        .param("userEmail","example@test.com")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Mockito.verify(this.todoListService, Mockito.times(0)).getTodoListByUserEmail(any());
    }



    private CreateTodoListRequestDto createTodoListRequest() {
        CreateTodoListRequestDto createTodoListRequestDto = new CreateTodoListRequestDto();
        createTodoListRequestDto.setTodoListDescription("test");
        createTodoListRequestDto.setTodoListName("test");
        createTodoListRequestDto.setTodoListStatus("ACTIVE");
        createTodoListRequestDto.setUserEmail("test@test.com");
        return createTodoListRequestDto;
    }
}
