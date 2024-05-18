package usermanagement.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.NonTransientDataAccessException;
import usermanagement.dto.UserDto;
import usermanagement.entity.User;
import usermanagement.exception.EmailAlreadyExistsException;
import usermanagement.mapper.AutoUserMapper;
import usermanagement.mapper.AutoUserMapperImpl;
import usermanagement.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.EMAIL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {
    private static final Long
            ID = 1L
            ;

    private static final String
            LAST_NAME = "Admin1"
            ;
    private static final String
            FIRST_NAME ="Test1"
            ;
    private static final String
            EMAIL= "test1T@test.test";


    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository
            userRepository;
    @Mock
    private AutoUserMapper autoUserMapper;
    @Mock
    private AutoUserMapperImpl autoUserMapperImpl;
    private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    private static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";

    private User user;
private UserDto userDto;
private Optional<User> optionalUser;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }
    @Test
    void whenCreateUserThenReturnEmailAlreadyExistsException() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        Exception exception = assertThrows(
                EmailAlreadyExistsException.class, () -> {
            userServiceImpl.createUser(userDto);
        });

        String expectedMessage = "Email already exists for user";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(user);

        UserDto
                response = userServiceImpl.createUser(userDto);

        assertNotNull(response);
        assertEquals(
                UserDto.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(FIRST_NAME, response.getFirstName());
        assertEquals(LAST_NAME, response.getLastName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenGetUserIdThenReturnAnUserInstance() {

        when(userRepository.findById(anyLong())).thenReturn(
             optionalUser);

        UserDto response = userServiceImpl.getUserById(ID);
        assertNotNull(response);
        assertEquals(UserDto.class,response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(FIRST_NAME,response.getFirstName());
        assertEquals(LAST_NAME,response.getLastName());
        assertEquals(EMAIL,response.getEmail());
        Mockito.verify(userRepository,Mockito.times(1)).findById(ID);
      Mockito.verifyNoMoreInteractions(userRepository);
        Mockito.verifyNoMoreInteractions(autoUserMapper);


    }

        @Test
        void whenFindByIdThenReturnAnObjectNotFoundException() {
            long id = 1L;
            String notFoundMessage = "Objeto não encontrado";
            String entityName = User.class.getName();

            doThrow(new ObjectNotFoundException(Optional.of(notFoundMessage), entityName))
                    .when(userRepository).findById(anyLong());

            try {
                userServiceImpl.getUserById(id);
            } catch (Exception ex) {
                assertEquals(ObjectNotFoundException.class, ex.getClass());

            }
        }
    @Test
    void whenRepositoryThrowsDataIntegrityViolationExceptionThenReturnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenThrow(new DataIntegrityViolationException("Email already exists in the system"));

        try {
            userServiceImpl.createUser(userDto);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals("Email already exists in the system", ex.getMessage());
        }
    }





    @Test
    void whenRepositoryThrowsIllegalArgumentExceptionThenReturnIllegalArgumentException() {
        when(userRepository.findByEmail(anyString())).thenThrow(new IllegalArgumentException("Invalid input"));

        try {
            userServiceImpl.createUser(userDto);
        } catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertEquals("Invalid input", ex.getMessage());
        }
    }

    @Test
    void whenRepositoryThrowsRuntimeExceptionThenReturnRuntimeException() {
        when(userRepository.findByEmail(anyString())).thenThrow(new RuntimeException("Unexpected error"));

        try {
            userServiceImpl.createUser(userDto);
        } catch (Exception ex) {
            assertEquals(RuntimeException.class, ex.getClass());
            assertEquals("Unexpected error", ex.getMessage());
        }
    }


    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(userRepository.findAll()).thenReturn(
                List.of(user));
        List<UserDto> response = userServiceImpl.getAllUsers();
        assertNotNull(response);
        assertTrue(response instanceof List);
        assertEquals(1,response.size());
        Mockito.verify(userRepository,Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(userRepository);
        Mockito.verifyNoMoreInteractions(autoUserMapper);
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        UserDto response = userServiceImpl.updateUser(userDto);
        assertNotNull(response);
        assertEquals(UserDto.class, response.getClass());
    }
    @Test
void whenDeleteThenReturnObjectNotFoundException() {
    when(userRepository.findById(anyLong()))
            .thenThrow(new ObjectNotFoundException(
                    Optional.of(
                            OBJETO_NAO_ENCONTRADO), User.class.getName()));
    try {
        userServiceImpl.deleteUser(ID);
    } catch (Exception ex) {
        assertEquals(ObjectNotFoundException.class, ex.getClass());

    }
}
    @Test
    void  deleteWithSuccess() {
        when(userRepository.findById(anyLong())).thenReturn(
                optionalUser);
        doNothing().when(userRepository).deleteById(anyLong());
        userServiceImpl.deleteUser(ID);
        Mockito.verify(userRepository,Mockito.times(1)).deleteById(ID);
        try {

            Mockito.verifyNoMoreInteractions(autoUserMapper);
        } catch (
                Exception e) {
            throw new RuntimeException(
                    e);
        }
    }
    private void startUser(){
        user = new User(ID,FIRST_NAME,LAST_NAME,EMAIL);
        userDto = new UserDto(ID,FIRST_NAME,LAST_NAME,EMAIL);
        optionalUser = Optional.of(new User(ID,FIRST_NAME,LAST_NAME,EMAIL));
}}