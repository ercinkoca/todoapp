package com.ercin.user.todoapp.controller;

import com.ercin.user.todoapp.dto.CreateUserRequestDto;
import com.ercin.user.todoapp.dto.CreateUserResponseDto;
import com.ercin.user.todoapp.dto.RestApiResponse;
import com.ercin.user.todoapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "getUser", description = "get user operation")
    @GetMapping("/getUser")
    public RestApiResponse<CreateUserResponseDto> getUser(@RequestParam(name = "email") String email) {
        CreateUserResponseDto createUserResponseDto = userService.getUser(email);
        return new RestApiResponse<>(createUserResponseDto);
    }

    @Operation(summary = "updateUser", description = "create user operation")
    @PutMapping("/update")
    public RestApiResponse<CreateUserResponseDto> updateUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        CreateUserResponseDto createUserResponseDto = userService.updateUser(createUserRequestDto);
        return new RestApiResponse<>(createUserResponseDto);
    }
}
