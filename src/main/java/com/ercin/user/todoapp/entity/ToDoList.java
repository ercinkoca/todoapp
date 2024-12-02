package com.ercin.user.todoapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

@Document
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class ToDoList extends GenericEntity {

    @Id
    @GeneratedValue(strategy = UNIQUE)
    String id;
    @Field
    String userId;
    @Field
    String todoListName;
    @Field
    String todoListDescription;
    @Field
    String todoListStatus;


}
