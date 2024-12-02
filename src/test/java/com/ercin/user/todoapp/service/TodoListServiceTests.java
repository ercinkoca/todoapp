package com.ercin.user.todoapp.service;

import com.ercin.user.todoapp.dto.CreateTodoListRequestDto;
import com.ercin.user.todoapp.dto.CreateTodoListResponseDto;
import com.ercin.user.todoapp.entity.ToDoList;
import com.ercin.user.todoapp.entity.Users;
import com.ercin.user.todoapp.exception.ServiceRuntimeException;
import com.ercin.user.todoapp.repository.TodoListRepository;
import com.ercin.user.todoapp.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class TodoListServiceTests {

    @InjectMocks
    private TodoListServiceImpl todoListService;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private TodoListRepository todoListRepository;


    @Test
    void createTodoListTest() {
        when(usersRepository.findByEmail(anyString())).thenReturn(createMockUser());
        when(todoListRepository.save(any())).thenReturn(createMockTodoList());
        CreateTodoListRequestDto requestDto = createTodoListRequest();
        CreateTodoListResponseDto responseDto = todoListService.createTodoList(requestDto);
        assertNotNull(responseDto);
        assertEquals("test", responseDto.getTodoListName());
    }

    @Test
    void createTodoListTestWhenUserIsNull() {
        try {
            when(usersRepository.findByEmail(anyString())).thenReturn(null);
            CreateTodoListRequestDto requestDto = createTodoListRequest();
            CreateTodoListResponseDto responseDto = todoListService.createTodoList(requestDto);
            assertNull(responseDto);
        } catch (ServiceRuntimeException ex) {
            assertEquals("User Not Found!", ex.getMessage());
        }
    }

    @Test
    void getTodoListTest() {
        when(todoListRepository.findByTodoListName(any())).thenReturn(createMockTodoList());
        CreateTodoListResponseDto responseDto = todoListService.getTodoList("test");
        assertNotNull(responseDto);
        assertEquals("test", responseDto.getTodoListName());
    }

    @Test
    void getTodoListTestWhenListIsNotFound() {
        try {
            when(todoListRepository.findByTodoListName(any())).thenReturn(null);
            CreateTodoListResponseDto responseDto = todoListService.getTodoList("test");
            assertNull(responseDto);
        } catch (ServiceRuntimeException ex) {
            assertEquals("Todo List Not Found", ex.getMessage());
        }
    }

    @Test
    void getTodoListByUserEmailTest() {
        when(usersRepository.findByEmail(anyString())).thenReturn(createMockUser());
        List<ToDoList> todoLists = new ArrayList<>();
        todoLists.add(createMockTodoList());
        when(todoListRepository.findByUserId(any())).thenReturn(todoLists);
        List<CreateTodoListResponseDto> responseDto = todoListService.getTodoListByUserEmail("test@test.com");
        assertNotNull(responseDto);
        assertNotNull(responseDto.get(0));
        assertEquals("test", responseDto.get(0).getTodoListName());
    }

    @Test
    void getTodoListByUserEmailTestWhenUserIsNotFound() {
        try {
            when(usersRepository.findByEmail(anyString())).thenReturn(null);
            List<CreateTodoListResponseDto> responseDto = todoListService.getTodoListByUserEmail("test@test.com");
            assertNull(responseDto);
        } catch (ServiceRuntimeException ex) {
            assertEquals("User Not Found", ex.getMessage());
        }
    }

    @Test
    void updateTodoListTest() {
        when(todoListRepository.save(any())).thenReturn(createMockTodoList());
        when(todoListRepository.findByTodoListName(any())).thenReturn(createMockTodoList());
        CreateTodoListRequestDto requestDto = createTodoListRequest();
        CreateTodoListResponseDto responseDto = todoListService.updateTodoList(requestDto);
        assertNotNull(responseDto);
        assertEquals("test", responseDto.getTodoListName());
    }

    @Test
    void updateTodoListTestWhenListIsNotFound() {
        try {
            when(todoListRepository.findByTodoListName(any())).thenReturn(null);
            CreateTodoListRequestDto requestDto = createTodoListRequest();
            CreateTodoListResponseDto responseDto = todoListService.updateTodoList(requestDto);
            assertNull(responseDto);
        } catch (ServiceRuntimeException ex) {
            assertEquals("Todo List Not Found", ex.getMessage());
        }

    }


    @Test
    void createTodoListRequestTests() {
        CreateTodoListRequestDto requestDto = createTodoListRequest();
        assertNotNull(requestDto);
        assertNotNull(requestDto.getTodoListStatus());
        assertNotNull(requestDto.getTodoListName());
        assertNotNull(requestDto.getTodoListDescription());
        assertNotNull(requestDto.getUserEmail());
    }

    @Test
    void createTodoListResponseTests() {
        CreateTodoListResponseDto responseDto = createTodoListResponse();
        assertNotNull(responseDto);
        assertNotNull(responseDto.getCreatedAt());
        assertNotNull(responseDto.getUpdatedAt());
        assertNotNull(responseDto.getStatus());
        assertEquals("test", responseDto.getTodoListName());
    }


    private CreateTodoListRequestDto createTodoListRequest() {
        CreateTodoListRequestDto createTodoListRequestDto = new CreateTodoListRequestDto();
        createTodoListRequestDto.setTodoListDescription("test");
        createTodoListRequestDto.setTodoListName("test");
        createTodoListRequestDto.setTodoListStatus("ACTIVE");
        createTodoListRequestDto.setUserEmail("test@test.com");
        return createTodoListRequestDto;
    }

    private CreateTodoListResponseDto createTodoListResponse() {
        CreateTodoListResponseDto responseDto = new CreateTodoListResponseDto();
        responseDto.setCreatedAt(new Date());
        responseDto.setUpdatedAt(new Date());
        responseDto.setStatus("ACTIVE");
        responseDto.setTodoListName("test");
        responseDto.setTodoListId("123456467");
        return responseDto;
    }


    private ToDoList createMockTodoList() {
        ToDoList toDoList = new ToDoList();
        toDoList.setUpdatedDate(new Date());
        toDoList.setUserId("teest");
        toDoList.setCreatedDate(new Date());
        toDoList.setTodoListStatus("ACTIVE");
        toDoList.setId("123456");
        toDoList.setTodoListDescription("test");
        toDoList.setTodoListName("test");
        return toDoList;
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


}
