package com.ercin.user.todoapp.service;

import com.ercin.user.todoapp.dto.CreateUserRequestDto;
import com.ercin.user.todoapp.dto.CreateUserResponseDto;
import com.ercin.user.todoapp.entity.Users;
import com.ercin.user.todoapp.enumaration.ReturnCodes;
import com.ercin.user.todoapp.exception.ServiceRuntimeException;
import com.ercin.user.todoapp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateUserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
        emailAndPasswordCheck(createUserRequestDto.getEmail(), createUserRequestDto.getPassword(),
                createUserRequestDto.getConfirmPassword());
        Users user = getUserWithEmail(createUserRequestDto.getEmail());
        if (user != null) {
            throw new ServiceRuntimeException("User already defined!", ReturnCodes.USER_ALREADY_DEFINED);
        }
        CreateUserResponseDto createUserResponseDto = new CreateUserResponseDto();
        Users userToCreated = createUserDtoToEntity(null, createUserRequestDto);
        Users savedUser = usersRepository.save(userToCreated);
        createUserResponseDto.setUsername(savedUser.getFirstName() + " " + savedUser.getLastName());
        return createUserResponseDto;
    }

    @Override
    public CreateUserResponseDto getUser(String email) {
        CreateUserResponseDto createUserResponseDto = new CreateUserResponseDto();
        Users user = usersRepository.findByEmail(email);
        if (user != null) {
            createUserResponseDto.setUsername(user.getFirstName() + " " + user.getLastName());
            createUserResponseDto.setEmail(user.getEmail());
            createUserResponseDto.setId(user.getId());
            return createUserResponseDto;
        } else {
            throw new ServiceRuntimeException("User Not Found", ReturnCodes.USER_NOT_FOUND);
        }
    }

    @Override
    public CreateUserResponseDto updateUser(CreateUserRequestDto createUserRequestDto) {
        emailAndPasswordCheck(createUserRequestDto.getEmail(), createUserRequestDto.getPassword(),
                createUserRequestDto.getConfirmPassword());
        CreateUserResponseDto createUserResponseDto = new CreateUserResponseDto();
        Users user = usersRepository.findByEmail(createUserRequestDto.getEmail());
        if (user != null) {
            Users updatedUser = createUserDtoToEntity(user, createUserRequestDto);
            Users savedUser = usersRepository.save(updatedUser);
            createUserResponseDto.setUsername(savedUser.getFirstName() + " " + savedUser.getLastName());
            createUserResponseDto.setId(savedUser.getId());
            createUserResponseDto.setEmail(savedUser.getEmail());
            return createUserResponseDto;
        } else {
            throw new ServiceRuntimeException("User Not Found", ReturnCodes.USER_NOT_FOUND);
        }
    }

    @Override
    public void deleteUser(String email) {
        Users user = usersRepository.findByEmail(email);
        usersRepository.delete(user);
    }

    @Override
    public Users getUserWithEmail(String email) {
        return usersRepository.findByEmail(email);
    }


    private Users createUserDtoToEntity(Users user, CreateUserRequestDto createUserRequestDto) {
        Users userEntity = new Users();
        if (user != null) {
            userEntity.setId(user.getId());
        }
        userEntity.setEmail(createUserRequestDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(createUserRequestDto.getPassword()));
        userEntity.setConfirmPassword(passwordEncoder.encode(createUserRequestDto.getPassword()));
        userEntity.setFirstName(createUserRequestDto.getFirstName());
        userEntity.setLastName(createUserRequestDto.getLastName());
        userEntity.setPhone(createUserRequestDto.getPhone());
        userEntity.setCreatedDate(new Date());
        userEntity.setUpdatedDate(new Date());
        return userEntity;
    }

    private void emailAndPasswordCheck(String email, String password, String confirmPassword) {
        if (StringUtils.isEmpty(email)) {
            throw new ServiceRuntimeException("Email can not be empty!", ReturnCodes.EMAIL_CANNOT_BLANK);
        }
        if (!password.equals(confirmPassword)) {
            throw new ServiceRuntimeException("Password and ConfirmedPassword should be same!", ReturnCodes.PASSWORD_SHOULD_SAME);
        }
    }


}
