package com.ercin.user.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTodoListRequestDto {
    private String todoListName;
    private String todoListDescription;
    private String todoListStatus;
    private String userEmail;
}
