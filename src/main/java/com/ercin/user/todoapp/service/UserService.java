package com.ercin.user.todoapp.service;

import com.ercin.user.todoapp.dto.CreateUserRequestDto;
import com.ercin.user.todoapp.dto.CreateUserResponseDto;
import com.ercin.user.todoapp.entity.Users;

public interface UserService {

    CreateUserResponseDto createUser(CreateUserRequestDto createUserRequestDto);
    CreateUserResponseDto getUser(String email);
    CreateUserResponseDto updateUser(CreateUserRequestDto createUserRequestDto);
    void deleteUser(String email);
    Users getUserWithEmail(String email);
}
