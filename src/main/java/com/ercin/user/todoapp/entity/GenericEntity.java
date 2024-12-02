package com.ercin.user.todoapp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.Date;

@Document
@AllArgsConstructor
@Data
@NoArgsConstructor
public class GenericEntity {

    @Field
    Date createdDate;
    @Field
    Date updatedDate;
}
