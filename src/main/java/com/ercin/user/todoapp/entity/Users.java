package com.ercin.user.todoapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

@Document
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Users extends GenericEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = UNIQUE)
    String id;
    @Field
    String firstName;
    @Field
    String lastName;
    @Field
    String email;
    @Field
    String password;
    @Field
    String confirmPassword;
    @Field
    String phone;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }
}
