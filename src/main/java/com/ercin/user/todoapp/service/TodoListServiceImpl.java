package com.ercin.user.todoapp.service;


import com.ercin.user.todoapp.dto.CreateTodoListRequestDto;
import com.ercin.user.todoapp.dto.CreateTodoListResponseDto;
import com.ercin.user.todoapp.entity.ToDoList;
import com.ercin.user.todoapp.entity.Users;
import com.ercin.user.todoapp.enumaration.ReturnCodes;
import com.ercin.user.todoapp.enumaration.TodoListStatus;
import com.ercin.user.todoapp.exception.ServiceRuntimeException;
import com.ercin.user.todoapp.repository.TodoListRepository;
import com.ercin.user.todoapp.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;

    private final UsersRepository usersRepository;

    @Override
    public CreateTodoListResponseDto createTodoList(CreateTodoListRequestDto createTodoListRequestDto) {
        Users user = usersRepository.findByEmail(createTodoListRequestDto.getUserEmail());
        if (user == null) {
            throw new ServiceRuntimeException("User Not Found!",ReturnCodes.USER_NOT_FOUND);
        }
        ToDoList toDoList = new ToDoList();
        toDoList.setTodoListStatus(TodoListStatus.ACTIVE.name());
        toDoList.setTodoListName(createTodoListRequestDto.getTodoListName());
        toDoList.setTodoListDescription(createTodoListRequestDto.getTodoListDescription());
        toDoList.setUserId(user.getId());
        toDoList.setCreatedDate(new Date());
        toDoList.setUpdatedDate(null);
        todoListRepository.save(toDoList);
        return mapTodoListResponseDto(toDoList);
    }

    @Override
    public CreateTodoListResponseDto getTodoList(String name) {
        ToDoList toDoList = todoListRepository.findByTodoListName(name);
        if (toDoList == null) {
            throw new ServiceRuntimeException("Todo List Not Found", ReturnCodes.TODO_LIST_NOT_FOUND);
        }
        return mapTodoListResponseDto(toDoList);
    }

    @Override
    public List<CreateTodoListResponseDto> getTodoListByUserEmail(String email) {
        Users user = usersRepository.findByEmail(email);
        if (user == null) {
            throw new ServiceRuntimeException("User Not Found", ReturnCodes.USER_NOT_FOUND);
        }

        List<ToDoList> todoList = todoListRepository.findByUserId(user.getId());
        List<CreateTodoListResponseDto> responseTodoList = new ArrayList<>();

        for (ToDoList toDoList : todoList) {
            CreateTodoListResponseDto responseDto = mapTodoListResponseDto(toDoList);
            responseTodoList.add(responseDto);
        }
        return responseTodoList;
    }

    @Override
    public CreateTodoListResponseDto updateTodoList(CreateTodoListRequestDto createTodoListRequestDto) {
        ToDoList toDoList = todoListRepository.findByTodoListName(createTodoListRequestDto.getTodoListName());
        if (toDoList == null) {
            throw new ServiceRuntimeException("Todo List Not Found", ReturnCodes.TODO_LIST_NOT_FOUND);
        }
        toDoList.setTodoListDescription(createTodoListRequestDto.getTodoListDescription());
        toDoList.setTodoListName(createTodoListRequestDto.getTodoListName());
        toDoList.setTodoListStatus(createTodoListRequestDto.getTodoListStatus());
        toDoList.setUpdatedDate(new Date());
        todoListRepository.save(toDoList);
        return mapTodoListResponseDto(toDoList);
    }

    @Override
    public void deleteTodoList(String name) {
        ToDoList toDoList = todoListRepository.findByTodoListName(name);
        if (toDoList == null) {
            throw new ServiceRuntimeException("Todo List Not Found", ReturnCodes.TODO_LIST_NOT_FOUND);
        }
        todoListRepository.delete(toDoList);
    }

    @Override
    public void deleteAllTodoListsByUserEmail(String email) {
        Users user = usersRepository.findByEmail(email);
        if (user == null) {
            throw new ServiceRuntimeException("User Not Found", ReturnCodes.USER_NOT_FOUND);
        }
        List<ToDoList> todoList = todoListRepository.findByUserId(user.getId());
        todoListRepository.deleteAll(todoList);
    }


    private CreateTodoListResponseDto mapTodoListResponseDto(ToDoList toDoList) {
        CreateTodoListResponseDto responseDto = new CreateTodoListResponseDto();
        responseDto.setTodoListName(toDoList.getTodoListName());
        responseDto.setTodoListId(toDoList.getId());
        responseDto.setStatus(toDoList.getTodoListStatus());
        responseDto.setCreatedAt(toDoList.getCreatedDate());
        responseDto.setUpdatedAt(toDoList.getUpdatedDate());
        return responseDto;
    }


}
