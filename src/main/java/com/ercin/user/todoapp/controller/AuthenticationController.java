package com.ercin.user.todoapp.controller;

import com.ercin.user.todoapp.dto.*;
import com.ercin.user.todoapp.entity.Users;
import com.ercin.user.todoapp.service.AuthenticationService;
import com.ercin.user.todoapp.service.JWTServiceImpl;
import com.ercin.user.todoapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JWTServiceImpl jwtService;
    private final UserService userService;

    @Operation(summary = "signup", description = "signup user operation")
    @PostMapping("/signup")
    public RestApiResponse<CreateUserResponseDto> signupUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        CreateUserResponseDto createUserResponseDto = authenticationService.signUp(createUserRequestDto);
        return new RestApiResponse<>(createUserResponseDto);
    }

    @Operation(summary = "login", description = "login user operation")
    @PostMapping("/login")
    public RestApiResponse<LoginResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
        CreateUserResponseDto createUserResponseDto = authenticationService.login(loginRequestDto);
        Users user = userService.getUserWithEmail(createUserResponseDto.getEmail());
        String jwtToken = jwtService.generateToken(user);
        Long expiresIn = jwtService.getExpirationTime();
        return new RestApiResponse<>(new LoginResponseDto(jwtToken, createUserResponseDto.getEmail(), expiresIn));
    }
}
