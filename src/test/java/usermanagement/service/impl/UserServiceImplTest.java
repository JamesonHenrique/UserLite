package usermanagement.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import usermanagement.dto.UserDto;
import usermanagement.entity.User;
import usermanagement.exception.EmailAlreadyExistsException;
import usermanagement.mapper.AutoUserMapper;
import usermanagement.mapper.AutoUserMapperImpl;
import usermanagement.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.EMAIL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class UserServiceImplTest {

        private static final Long   ID   =  1L;
        private static final Integer INDEX   = 0;
        private static final String NAME_FIRST     = "James";
        private static final String NAME_LAST     = "Bond";
        private static final String EMAIL    = "james@mail.com";
        private static final String PASSWORD = "123";

        private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
        private static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";

        @InjectMocks
        private UserServiceImpl service;

        @Mock
        private UserRepository
                repository;
        @Mock
        private ModelMapper
                mapper;



        private User user;
        private UserDto userDto;
        private Optional<User> optionalUser;


        @BeforeEach
        void setUp() {

                MockitoAnnotations.openMocks(this);
          startUser();

        }

        @Test
        void whenFindByIdThenReturnAnUserInstance()
        {
            when(repository.findById(anyLong())).thenReturn(optionalUser);
            User
                    response = service.getUserById(ID);

            assertNotNull(response);

            assertEquals(ID, response.getId());
            assertEquals(NAME_FIRST, response.getFirstName());
            assertEquals(NAME_LAST, response.getLastName());
            assertEquals(EMAIL, response.getEmail());
            assertEquals(PASSWORD, response.getPassword());
        }



        @Test
        void whenFindAllThenReturnAnListOfUsers() {
            Mockito.when(repository.findAll()).thenReturn(List.of(user));

            List<User> response = service.getAllUsers();

            assertNotNull(response);
            assertEquals(1, response.size());
            assertEquals(User.class, response.get(INDEX).getClass());

            assertEquals(ID, response.get(INDEX).getId());
            assertEquals(NAME_FIRST, response.get(INDEX).getFirstName());
            assertEquals(NAME_LAST, response.get(INDEX).getLastName());
            assertEquals(EMAIL, response.get(INDEX).getEmail());
            assertEquals(PASSWORD, response.get(INDEX).getPassword());
        }

        @Test
        void whenCreateThenReturnSuccess() {
            when(repository.save(any())).thenReturn(user);

            User
                    response = service.createUser(userDto);

            assertNotNull(response);
            assertEquals(User.class, response.getClass());
            assertEquals(ID, response.getId());
            assertEquals(NAME_FIRST, response.getFirstName());
            assertEquals(NAME_LAST, response.getLastName());
            assertEquals(EMAIL, response.getEmail());
            assertEquals(PASSWORD, response.getPassword());
        }



        @Test
        void whenUpdateThenReturnSuccess() {
            when(repository.save(any())).thenReturn(user);

            User
                    response = service.updateUser(userDto);

            assertNotNull(response);
            assertEquals(User.class, response.getClass());
            assertEquals(ID, response.getId());
   assertEquals(NAME_LAST, response.getLastName());
            assertEquals(EMAIL, response.getEmail());
            assertEquals(PASSWORD, response.getPassword());
        }



        @Test
        void deleteWithSuccess() {
            when(repository.findById(anyLong())).thenReturn(optionalUser);
            doNothing().when(repository).deleteById(anyLong());
            service.deleteUser(ID);
            verify(repository, times(1)).deleteById(anyLong());
        }

        @Test
        void whenDeleteThenReturnObjectNotFoundException() {
            when(repository.findById(anyLong()))
                    .thenThrow(new ObjectNotFoundException(
                            Optional.of(
                                    OBJETO_NAO_ENCONTRADO), User.class.getName()));
            try {
                service.deleteUser(ID);
            } catch (Exception ex) {
                assertEquals(ObjectNotFoundException.class, ex.getClass());

            }
        }

        private void startUser() {
            user = new User(ID, NAME_FIRST,NAME_LAST, EMAIL,  PASSWORD);
            userDto = new UserDto(ID,NAME_FIRST,NAME_LAST, EMAIL,  PASSWORD);
            optionalUser = Optional.of(new User(ID, NAME_FIRST,NAME_LAST, EMAIL,  PASSWORD));
        }
    }