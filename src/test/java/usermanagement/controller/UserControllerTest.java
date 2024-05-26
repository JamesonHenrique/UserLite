//package usermanagement.controller;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import usermanagement.dto.UserDto;
//
//import usermanagement.dto.UserResponseDto;
//import usermanagement.entity.User;
//import usermanagement.service.impl.UserServiceImpl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//
//class UserControllerTest {
//    @InjectMocks
//    private UserController controller;
//    @Mock
//    private ModelMapper modelMapper;
//
//    @Mock
//    private UserServiceImpl
//            service;
//    private static final Long   ID   =  1L;
//    private static final Integer INDEX   = 0;
//    private static final String FIRST_NAME     = "James";
//    private static final String  LAST_NAME   = "Bond";
//    private static final String EMAIL    = "james@mail.com";
//    private static final String PASSWORD = "123";
//    @Mock
//    private ModelMapper mapper;
//    @Mock
//    private Authentication authentication;
//    private User user;
//    private UserResponseDto userResponseDto;
//    private UserDto userDto;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        startUser();
//    }
//    private void startUser() {
//        user = new User(ID, FIRST_NAME,LAST_NAME, EMAIL, PASSWORD);
//        userResponseDto = new UserResponseDto(ID, FIRST_NAME,LAST_NAME, EMAIL);
//        userDto = new UserDto(ID, FIRST_NAME,LAST_NAME, EMAIL, PASSWORD);
//    }
//
//    @Test
//    void whenGetUserByIdThenReturnSuccess() {
//        when(service.getUserById(anyLong())).thenReturn(user);
//        when(mapper.map(any(), any())).thenReturn(userDto);
//
//        ResponseEntity<UserResponseDto> response = controller.getUserById(ID);
//
//        assertNotNull(response);
//        assertNotNull(response.getBody());
//        assertEquals(ResponseEntity.class, response.getClass());
//        assertEquals(UserResponseDto.class, response.getBody().getClass());
//
//        assertEquals(ID, response.getBody().getId());
//
//        assertEquals(FIRST_NAME, response.getBody().getFirstName());
//        assertEquals(LAST_NAME, response.getBody().getLastName());
//        assertEquals(EMAIL, response.getBody().getEmail());
//
//    }
//
//    @Test
//    void whenFindAllThenReturnAListOfuserDto() {
//        when(service.getAllUsers()).thenReturn(
//                List.of(user));
//        when(mapper.map(any(), any())).thenReturn(userDto);
//
//        ResponseEntity<List<UserResponseDto>> response = controller.getAllUsers();
//
//        assertNotNull(response);
//        assertNotNull(response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(ResponseEntity.class, response.getClass());
//        assertEquals(
//                ArrayList.class, response.getBody().getClass());
//        assertEquals(UserResponseDto.class, response.getBody().get(INDEX).getClass());
//
//        assertEquals(ID, response.getBody().get(INDEX).getId());
//
//        assertEquals(FIRST_NAME, response.getBody().get(INDEX).getFirstName());
//        assertEquals(LAST_NAME, response.getBody().get(INDEX).getLastName());
//        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
//
//    }
//
//    @Test
//    void whenCreateThenReturnCreated() {
//        when(service.createUser(any())).thenReturn(user);
//
//        ResponseEntity<UserDto> response = controller.createUser(userDto);
//
//        assertEquals(ResponseEntity.class, response.getClass());
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//
//    }
//
//    @Test
//    void whenUpdateThenReturnSuccess() {
//        when(service.updateUser(userDto)).thenReturn(user);
//        when(mapper.map(any(), any())).thenReturn(userDto);
//
//        ResponseEntity<UserDto> response = controller.updateUser(ID, userDto);
//
//        assertNotNull(response);
//        assertNotNull(response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(ResponseEntity.class, response.getClass());
//        assertEquals(UserDto.class, response.getBody().getClass());
//
//        assertEquals(ID, response.getBody().getId());
//
//        assertEquals(EMAIL, response.getBody().getEmail());
//    }
//
//    @Test
//    void whenDeleteThenReturnSuccess() {
//        doNothing().when(service).deleteUser(anyLong());
//
//        ResponseEntity<UserDto> response = controller.deleteUser(ID);
//
//        assertNotNull(response);
//        assertEquals(ResponseEntity.class, response.getClass());
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//        verify(service, times(1)).deleteUser(anyLong());
//    }
//
//}
