package usermanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.asm.TypeReference;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import usermanagement.dto.UserDto;

import usermanagement.entity.User;
import usermanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private static final String ID = "/{id}";

    private UserService userService;
private ModelMapper mapper;
    @PostMapping
    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED")

public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user) {
        User createdUser = userService.createUser(user);
        UserDto userDto = mapper.map(createdUser, UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
}
    @GetMapping("{id}")
    @Operation(
            summary = "Get User By ID REST API",
            description = "Get User By ID REST API is used to get a single user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        return ResponseEntity.ok().body(mapper.map(userService.getUserById(userId), UserDto.class));
    }


    @GetMapping@Operation(
            summary = "Get All Users REST API",
            description = "Get All Users REST API is used to get a all the users from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )

    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtos = users.stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(
                        Collectors.toList());
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }


    @PutMapping("{id}")
    @Operation(
            summary = "Update User REST API",
            description = "Update User REST API is used to update a particular user in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody @Valid UserDto user){
        user.setId(userId);
        return ResponseEntity.ok().body(mapper.map(userService.updateUser(user), UserDto.class));
    }
    @Operation(
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete a particular user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )

    @DeleteMapping("{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);

        return ResponseEntity.noContent().build();
    }

    }

