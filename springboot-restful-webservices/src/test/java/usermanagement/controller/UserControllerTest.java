package usermanagement.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import usermanagement.dto.UserDto;
import usermanagement.entity.User;
import usermanagement.mapper.AutoUserMapper;
import usermanagement.mapper.AutoUserMapperImpl;
import usermanagement.service.impl.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest

class UserControllerTest {
    private UserController userController;
    private static final Long ID      = 1L;

    private static final String FIRST_NAME     = "Admin";
    private static final String LAST_NAME      = "Test";
    private static final String EMAIL    = "test@mail.com";


    private User
            user = new User();
    private UserDto
            userDto = new UserDto();

    @InjectMocks
    private UserController resource;

    @Mock
    private UserServiceImpl
            service;
@Mock
private AutoUserMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }
    private void startUser() {
        user = new User(ID, FIRST_NAME,LAST_NAME, EMAIL);
        userDto = new UserDto(ID, FIRST_NAME,LAST_NAME, EMAIL);
    }
    @Test
    void whenCreateThenReturnCreated() {
        when(service.createUser(any(UserDto.class))).thenReturn(userDto);

        ResponseEntity<UserDto> response = resource.createUser(userDto);


        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    void getUserById() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}