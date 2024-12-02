package com.ercin.user.todoapp.service;

import com.ercin.user.todoapp.dto.CreateUserRequestDto;
import com.ercin.user.todoapp.dto.CreateUserResponseDto;
import com.ercin.user.todoapp.dto.LoginRequestDto;
import com.ercin.user.todoapp.entity.Users;
import com.ercin.user.todoapp.enumaration.ReturnCodes;
import com.ercin.user.todoapp.exception.ServiceRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateUserResponseDto signUp(CreateUserRequestDto createUserRequestDto) {
        return userService.createUser(createUserRequestDto);
    }

    @Override
    public CreateUserResponseDto login(LoginRequestDto loginRequestDto) {
        Users userToBeLogin = userService.getUserWithEmail(loginRequestDto.getEmail());
        if (userToBeLogin == null) {
            throw new ServiceRuntimeException("User Not Found", ReturnCodes.USER_NOT_FOUND);
        }
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), userToBeLogin.getPassword())) {
            throw new ServiceRuntimeException("Password Not Match!", ReturnCodes.PASSWORD_NOT_MATCH);
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userToBeLogin.getEmail(),
                        loginRequestDto.getPassword()
                )
        );
        return new CreateUserResponseDto(userToBeLogin.getId(), userToBeLogin.getUsername(), userToBeLogin.getEmail());
    }
}
