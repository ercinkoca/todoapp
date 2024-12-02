package com.ercin.user.todoapp.service;

import com.ercin.user.todoapp.dto.CreateTodoListRequestDto;
import com.ercin.user.todoapp.dto.CreateTodoListResponseDto;

import java.util.List;

public interface TodoListService {

    CreateTodoListResponseDto createTodoList(CreateTodoListRequestDto createTodoListRequestDto);
    CreateTodoListResponseDto getTodoList(String name);
    List<CreateTodoListResponseDto> getTodoListByUserEmail(String email);
    CreateTodoListResponseDto updateTodoList(CreateTodoListRequestDto createTodoListRequestDto);
    void deleteTodoList(String name);
    void deleteAllTodoListsByUserEmail(String email);
}
