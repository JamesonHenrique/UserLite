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
import org.springframework.dao.DataIntegrityViolationException;
import usermanagement.dto.UserDto;
import usermanagement.entity.User;
import usermanagement.mapper.AutoUserMapper;
import usermanagement.mapper.AutoUserMapperImpl;
import usermanagement.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.EMAIL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
class UserServiceImplTest {
    private static final Long
            ID = 1L
            ;

    private static final String
            LAST_NAME = "Admin"
            ;
    private static final String
            FIRST_NAME ="Test"
            ;
    private static final String
            EMAIL= "test@example.com";


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
    void whenCreateUserThenReturnUserDtoInstance() {
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(
                optionalUser);

        Mockito.when(autoUserMapper.mapToUser(userDto)).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(autoUserMapper.mapToUserDto(user)).thenReturn(userDto);
        UserDto response = userServiceImpl.createUser(userDto);
        assertNotNull(response);
        assertEquals(UserDto.class,response.getClass());

    }

    @Test
    void whenGetUserIdThenReturnAnUserInstance() {

        Mockito.when(userRepository.findById(anyLong())).thenReturn(
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

       Mockito. when(userRepository.findById(anyLong()))
                .thenThrow(new ObjectNotFoundException(
                        Optional.of(
                                OBJETO_NAO_ENCONTRADO), User.class.getName()));

        try{
            userServiceImpl.getUserById(ID);
        } catch (Exception ex) {
            assertEquals(
                    ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }
    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2L);
            userServiceImpl.createUser(userDto);
        } catch (Exception ex) {
            assertEquals(
                    DataIntegrityViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(
                List.of(user));
        List<UserDto> response = userServiceImpl.getAllUsers();
        assertNotNull(response);
        assertEquals(List.class,response.getClass());
        assertEquals(1,response.size());
        Mockito.verify(userRepository,Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(userRepository);
        Mockito.verifyNoMoreInteractions(autoUserMapper);
    }

    @Test
    void updateUser() {
        Mockito.when(userRepository.findById(anyLong())).thenReturn(
                optionalUser);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        UserDto response = userServiceImpl.updateUser(userDto);
        assertNotNull(response);
        assertEquals(UserDto.class,response.getClass());
    }
    @Test
    void whenDeleteThenReturnObjectNotFoundException() {
        Mockito.when(userRepository.findById(anyLong()))
                .thenThrow(new ObjectNotFoundException(    Optional.of(
                        OBJETO_NAO_ENCONTRADO), User.class.getName()));
        try {
            userServiceImpl.deleteUser(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }
    @Test
    void  deleteWithSuccess() {
        Mockito.when(userRepository.findById(anyLong())).thenReturn(
                optionalUser);
        doNothing().when(userRepository).deleteById(anyLong());
        userServiceImpl.deleteUser(ID);
        Mockito.verify(userRepository,Mockito.times(1)).deleteById(ID);
        try {
            Mockito.verifyNoMoreInteractions(userRepository);
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
        optionalUser = Optional.of(user);
}}