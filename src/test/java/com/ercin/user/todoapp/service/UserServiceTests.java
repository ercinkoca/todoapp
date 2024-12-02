package com.ercin.user.todoapp.service;

import com.ercin.user.todoapp.dto.CreateUserRequestDto;
import com.ercin.user.todoapp.dto.CreateUserResponseDto;
import com.ercin.user.todoapp.entity.Users;
import com.ercin.user.todoapp.exception.ServiceRuntimeException;
import com.ercin.user.todoapp.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTests {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void createUserTest() {
        CreateUserRequestDto requestDto = createUserRequestDto();
        when(usersRepository.findByEmail(anyString())).thenReturn(null);
        when(usersRepository.save(any())).thenReturn(createMockUser());
        when(passwordEncoder.encode(anyString())).thenReturn("test");
        CreateUserResponseDto response = userService.createUser(requestDto);
        assertNotNull(response);
        assertEquals("test2 test1", response.getUsername());
    }

    @Test
    void createUserTestWhenUserIsNotNull() {
        try {
            CreateUserRequestDto requestDto = createUserRequestDto();
            when(usersRepository.findByEmail(anyString())).thenReturn(createMockUser());
            when(usersRepository.save(any())).thenReturn(createMockUser());
            when(passwordEncoder.encode(anyString())).thenReturn("test");
            CreateUserResponseDto response = userService.createUser(requestDto);
            assertNull(response);
        } catch (ServiceRuntimeException ex) {
            assertEquals("User already defined!", ex.getMessage());
        }
    }

    @Test
    void createUserTestWhenUserEmailEmpty() {
        try {
            CreateUserRequestDto requestDto = createUserRequestDto();
            requestDto.setEmail("");
            when(usersRepository.findByEmail(anyString())).thenReturn(null);
            when(usersRepository.save(any())).thenReturn(createMockUser());
            when(passwordEncoder.encode(anyString())).thenReturn("test");
            CreateUserResponseDto response = userService.createUser(requestDto);
            assertNull(response);
        } catch (ServiceRuntimeException ex) {
            assertEquals("Email can not be empty!", ex.getMessage());
        }
    }

    @Test
    void createUserTestWhenPasswordIsNotMatch() {
        try {
            CreateUserRequestDto requestDto = createUserRequestDto();
            requestDto.setConfirmPassword("sdfsdfsdfsd");
            when(usersRepository.findByEmail(anyString())).thenReturn(null);
            when(usersRepository.save(any())).thenReturn(createMockUser());
            when(passwordEncoder.encode(anyString())).thenReturn("test");
            CreateUserResponseDto response = userService.createUser(requestDto);
            assertNull(response);
        } catch (ServiceRuntimeException ex) {
            assertEquals("Password and ConfirmedPassword should be same!", ex.getMessage());
        }
    }

    @Test
    void getUserTest() {
        when(usersRepository.findByEmail(anyString())).thenReturn(createMockUser());
        CreateUserResponseDto response = userService.getUser("test@test.com");
        assertNotNull(response);
        assertEquals("test@test.com", response.getEmail());
    }

    @Test
    void getUserTestWhenUserIsNotFound() {
        try {
            when(usersRepository.findByEmail(anyString())).thenReturn(null);
            CreateUserResponseDto response = userService.getUser("test@test.com");
            assertNull(response);
        } catch (ServiceRuntimeException ex) {
            assertEquals("User Not Found", ex.getMessage());
        }
    }

    @Test
    void updateUserTest() {
        CreateUserRequestDto requestDto = createUserRequestDto();
        when(usersRepository.findByEmail(anyString())).thenReturn(createMockUser());
        when(usersRepository.save(any())).thenReturn(createMockUser());
        when(passwordEncoder.encode(anyString())).thenReturn("test");
        CreateUserResponseDto response = userService.updateUser(requestDto);
        assertNotNull(response);
        assertEquals("test@test.com", response.getEmail());
    }

    @Test
    void updateUserTestWhenUserEmailEmpty() {
        try {
            CreateUserRequestDto requestDto = createUserRequestDto();
            requestDto.setEmail("");
            when(usersRepository.findByEmail(anyString())).thenReturn(null);
            when(usersRepository.save(any())).thenReturn(createMockUser());
            when(passwordEncoder.encode(anyString())).thenReturn("test");
            CreateUserResponseDto response = userService.updateUser(requestDto);
            assertNull(response);
        } catch (ServiceRuntimeException ex) {
            assertEquals("Email can not be empty!", ex.getMessage());
        }
    }

    @Test
    void updateUserTestWhenPasswordIsNotMatch() {
        try {
            CreateUserRequestDto requestDto = createUserRequestDto();
            requestDto.setConfirmPassword("sdfsdfsdfsd");
            when(usersRepository.findByEmail(anyString())).thenReturn(null);
            when(usersRepository.save(any())).thenReturn(createMockUser());
            when(passwordEncoder.encode(anyString())).thenReturn("test");
            CreateUserResponseDto response = userService.updateUser(requestDto);
            assertNull(response);
        } catch (ServiceRuntimeException ex) {
            assertEquals("Password and ConfirmedPassword should be same!", ex.getMessage());
        }
    }

    @Test
    void updateUserTestWhenUserIsNull() {
        try {
            CreateUserRequestDto requestDto = createUserRequestDto();
            when(usersRepository.findByEmail(anyString())).thenReturn(null);
            when(usersRepository.save(any())).thenReturn(createMockUser());
            when(passwordEncoder.encode(anyString())).thenReturn("test");
            CreateUserResponseDto response = userService.createUser(requestDto);
            assertNull(response.getId());
        } catch (ServiceRuntimeException ex) {
            assertEquals("User Not Found", ex.getMessage());
        }
    }

    @Test
    void getUserWithEmailTest() {
        when(usersRepository.findByEmail(anyString())).thenReturn(createMockUser());
        Users user = userService.getUserWithEmail("test@test.com");
        assertEquals("test2", user.getFirstName());
    }

    @Test
    void createResponseDtoTests() {
        CreateUserResponseDto responseDto = createUserResponse();
        assertNotNull(responseDto.getId());
        assertNotNull(responseDto.getEmail());
        assertNotNull(responseDto.getUsername());
    }

    @Test
    void createUserRequestDtoTest() {
        CreateUserRequestDto requestDto = createUserRequestDto();
        assertNotNull(requestDto.getEmail());
        assertNotNull(requestDto.getPassword());
        assertNotNull(requestDto.getPhone());
        assertNotNull(requestDto.getConfirmPassword());
        assertEquals("test2", requestDto.getFirstName());
        assertEquals("test1", requestDto.getLastName());
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

    private CreateUserResponseDto createUserResponse() {
        CreateUserResponseDto responseDto = new CreateUserResponseDto();
        responseDto.setEmail("test");
        responseDto.setId("test");
        responseDto.setUsername("test");
        return responseDto;
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
}
