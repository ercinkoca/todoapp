package com.ercin.user.todoapp.controller;

import com.ercin.user.todoapp.dto.*;
import com.ercin.user.todoapp.service.TodoListService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todoList")
@AllArgsConstructor
public class TodoListController {

    private final TodoListService todoListService;

    @Operation(summary = "createToDoList", description = "create to do list operation")
    @PostMapping("/createTodoList")
    public RestApiResponse<CreateTodoListResponseDto> createTodoList(@RequestBody CreateTodoListRequestDto createTodoListRequestDto) {
        CreateTodoListResponseDto responseDto = todoListService.createTodoList(createTodoListRequestDto);
        return new RestApiResponse<>(responseDto);
    }

    @Operation(summary = "getToDoList", description = "get to do list operation")
    @GetMapping("/getTodoList")
    public RestApiResponse<CreateTodoListResponseDto> getTodoList(@RequestParam(name = "todoListName") String todoListName) {
        CreateTodoListResponseDto responseDto = todoListService.getTodoList(todoListName);
        return new RestApiResponse<>(responseDto);
    }

    @Operation(summary = "getToDoListByUser", description = "get to do list operation")
    @GetMapping("/getTodoListByUserEmail")
    public RestApiResponse<List<CreateTodoListResponseDto>> getTodoListByUserEmail(@RequestParam(name = "userEmail") String userEmail) {
        List<CreateTodoListResponseDto> createTodoListResponseDtoList = todoListService.getTodoListByUserEmail(userEmail);
        return new RestApiResponse<>(createTodoListResponseDtoList);
    }

    @Operation(summary = "updateToDoList", description = "update to do list operation")
    @PutMapping("/updateTodoList")
    public RestApiResponse<CreateTodoListResponseDto> updateTodoList(@RequestBody CreateTodoListRequestDto createTodoListRequestDto) {
        CreateTodoListResponseDto responseDto = todoListService.updateTodoList(createTodoListRequestDto);
        return new RestApiResponse<>(responseDto);
    }

    @Operation(summary = "deleteToDoList", description = "delete to do list operation")
    @DeleteMapping("/deleteTodoList")
    public BaseRestResponse deleteTodoListByName(@NotNull @RequestParam(name = "todoListName") String todoListName) {
        todoListService.deleteTodoList(todoListName);
        return RestApiResponse.ok();
    }

}
