package com.ercin.user.todoapp.repository;

import com.ercin.user.todoapp.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends CrudRepository<Users, String> {

    Users findByFirstName(String username);
    Users findByEmail(String email);


}
