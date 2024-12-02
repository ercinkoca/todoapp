package com.ercin.user.todoapp.repository;

import com.ercin.user.todoapp.entity.ToDoList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListRepository extends CrudRepository<ToDoList, String> {

    ToDoList findByTodoListName(String todoListName);
    List<ToDoList> findByUserId(String userId);
}
