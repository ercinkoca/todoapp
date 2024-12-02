package com.ercin.user.todoapp.service;

import com.ercin.user.todoapp.entity.Users;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JwtServiceTests {

    @Autowired
    private JWTServiceImpl jwtService;

    private final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrb2NhZXJjaW4yQGdtYWlsLmNvbSIsImlhdCI6MTczMzA3NjY2MCwiZXhwIjoxNzMzMDgwMjYwfQ.EvxzEbsbZgBzl88wFtCtnM6psXlx1CUKnUEsm6spjyY";

    @Test
    void extractUsernameTest() {
        try {
            String username = jwtService.extractUsername(TOKEN);
        } catch (ExpiredJwtException ex) {
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    void generateTokenTests() {
        String token = jwtService.generateToken(createMockUser());
        assertNotNull(token);
    }

    @Test
    void getExpirationTimeTest() {
        Long expTime = jwtService.getExpirationTime();
        assertNotNull(expTime);
    }


    private Users createMockUser() {
        Users user = new Users();
        user.setUpdatedDate(new Date());
        user.setCreatedDate(new Date());
        user.setId("12345678");
        user.setFirstName("test2");
        user.setLastName("test1");
        user.setEmail("test@test.com");
        user.setPassword("123456");
        user.setConfirmPassword("123456");
        return user;
    }
}
