package com.ercin.user.todoapp.service;


import com.ercin.user.todoapp.dto.CreateUserRequestDto;
import com.ercin.user.todoapp.dto.CreateUserResponseDto;
import com.ercin.user.todoapp.dto.LoginRequestDto;
import com.ercin.user.todoapp.entity.Users;
import com.ercin.user.todoapp.exception.ServiceRuntimeException;
import com.ercin.user.todoapp.repository.TodoListRepository;
import com.ercin.user.todoapp.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AuthenticationServiceTests {

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    void loginRequestTest() {
        LoginRequestDto loginRequestDto = loginRequest();
        assertNotNull(loginRequestDto);
        assertNotNull(loginRequestDto.getPassword());
        assertNotNull(loginRequestDto.getEmail());
    }


    @Test
    void signUpTest() {
        when(userService.createUser(any())).thenReturn(createUserResponse());
        CreateUserResponseDto responseDto = authenticationService.signUp(createUserRequestDto());
        assertNotNull(responseDto);
        assertNotNull(responseDto.getUsername());
    }

    @Test
    void loginTest() {
        when(userService.getUserWithEmail(anyString())).thenReturn(createMockUser());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken("test", "test");
        when(authenticationManager.authenticate(any())).thenReturn(usernamePasswordAuthenticationToken);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        CreateUserResponseDto responseDto = authenticationService.login(loginRequest());
        assertNotNull(responseDto);
        assertNotNull(responseDto.getUsername());
    }

    @Test
    void loginTestWhenUserNotFound() {
        try {
            when(userService.getUserWithEmail(anyString())).thenReturn(null);
            CreateUserResponseDto responseDto = authenticationService.login(loginRequest());
            assertNull(responseDto);
        } catch (ServiceRuntimeException ex) {
            assertEquals("User Not Found", ex.getMessage());
        }
    }

    @Test
    void loginTestWhenPasswordNotMatch() {
        try {
            when(userService.getUserWithEmail(anyString())).thenReturn(createMockUser());
            when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
            CreateUserResponseDto responseDto = authenticationService.login(loginRequest());
            assertNull(responseDto);
        } catch (ServiceRuntimeException ex) {
            assertEquals("Password Not Match!", ex.getMessage());
        }
    }

    private Users createMockUser() {
        Users user = new Users();
        user.setUpdatedDate(new Date());
        user.setCreatedDate(new Date());
        user.setId("12345678");
        user.setFirstName("test2");
        user.setLastName("test1");
        user.setEmail("test@test.com");
        user.setPassword("123456");
        user.setConfirmPassword("123456");
        return user;
    }


    private LoginRequestDto loginRequest() {
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail("test");
        loginRequestDto.setPassword("test");
        return loginRequestDto;
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

    private CreateUserResponseDto createUserResponse() {
        CreateUserResponseDto responseDto = new CreateUserResponseDto();
        responseDto.setEmail("test");
        responseDto.setId("test");
        responseDto.setUsername("test");
        return responseDto;
    }

}
