package com.ercin.user.todoapp.service;

import com.ercin.user.todoapp.dto.CreateUserRequestDto;
import com.ercin.user.todoapp.dto.CreateUserResponseDto;
import com.ercin.user.todoapp.dto.LoginRequestDto;

public interface AuthenticationService {

    CreateUserResponseDto signUp(CreateUserRequestDto createUserRequestDto);
    CreateUserResponseDto login(LoginRequestDto loginRequestDto);
}
