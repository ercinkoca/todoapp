package com.ercin.user.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTodoListResponseDto {
    private String todoListId;
    private String todoListName;
    private String status;
    private Date createdAt;
    private Date updatedAt;
}
