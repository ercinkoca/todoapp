package com.ercin.user.todoapp.enumaration;


public enum TodoListStatus {

    ACTIVE( "ACTIVE"),
    CLOSED( "CLOSED"),
    IN_PROGRESS( "CLOSED");

    private final String description;

    TodoListStatus(String description) {
        this.description = description;
    }
}
