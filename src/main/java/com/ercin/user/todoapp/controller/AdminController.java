package com.ercin.user.todoapp.controller;

import com.ercin.user.todoapp.dto.BaseRestResponse;
import com.ercin.user.todoapp.dto.CreateUserRequestDto;
import com.ercin.user.todoapp.dto.CreateUserResponseDto;
import com.ercin.user.todoapp.dto.RestApiResponse;
import com.ercin.user.todoapp.service.TodoListService;
import com.ercin.user.todoapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final TodoListService todoListService;

    @Operation(summary = "createUser", description = "create user operation")
    @PostMapping("/createUser")
    public RestApiResponse<CreateUserResponseDto> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        CreateUserResponseDto createUserResponseDto = userService.createUser(createUserRequestDto);
        return new RestApiResponse<>(createUserResponseDto);
    }

    @Operation(summary = "deleteUser", description = "delete user operation")
    @DeleteMapping("/deleteUser")
    public BaseRestResponse deleteUser(@RequestParam(name = "email") String email) {
        userService.deleteUser(email);
        return RestApiResponse.ok();
    }

    @Operation(summary = "deleteAllToDoList", description = "get to do list operation")
    @DeleteMapping("/deleteAllTodoList")
    public BaseRestResponse deleteAllTodoListByEmail(@NotNull @RequestParam(name = "userEmail") String userEmail) {
        todoListService.deleteAllTodoListsByUserEmail(userEmail);
        return RestApiResponse.ok();
    }
}
