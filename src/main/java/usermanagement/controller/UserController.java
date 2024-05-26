package usermanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.asm.TypeReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import usermanagement.dto.UserDto;

import usermanagement.dto.UserResponseDto;
import usermanagement.email.EmailSender;
import usermanagement.email.PasswordResetToken;
import usermanagement.entity.User;
import usermanagement.exception.ResourceNotFoundException;
import usermanagement.repository.PasswordResetTokenRepository;
import usermanagement.service.PasswordResetTokenService;
import usermanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usermanagement.service.impl.PasswordResetTokenServiceImpl;
import usermanagement.service.impl.UserServiceImpl;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;
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

    private UserServiceImpl
            userService;
    private PasswordResetTokenServiceImpl
            passwordResetTokenServiceImpl;
    private EmailSender emailSender;
    private BCryptPasswordEncoder passwordEncoder;

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
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") Long userId){
        User user = userService.getUserById(userId);
        UserResponseDto userDto = mapper.map(user, UserResponseDto.class);
        return ResponseEntity.ok().body(userDto);  }


    @GetMapping@Operation(
            summary = "Get All Users REST API",
            description = "Get All Users REST API is used to get a all the users from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )

    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        List<UserResponseDto> userDtos = users.stream()
                .map(user -> mapper.map(user, UserResponseDto.class))
                .collect(
                        Collectors.toList());
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }


    @PatchMapping ("{id}")
    @Operation(
            summary = "Update User REST API",
            description = "Update User REST API is used to update a particular user in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody @Valid UserDto user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().
        stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("SCOPE_ADMIN"));
        if (!isAdmin && !authentication.getName().equals(user.getEmail())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        user.setId(userId);
        user.setPassword(userService.getUserById(userId).getPassword());

        return ResponseEntity.ok().body(mapper.map(userService.updateUser(user), UserResponseDto.class));
    }
    @GetMapping("/forgot-password")
    @Operation(
            summary = "Forgot Password REST API",
            description = "Forgot Password REST API is used to send an email to the user with a link to reset the password"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    public ResponseEntity<String> forgotPassword(@RequestParam String email){
        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();


            String token = UUID.randomUUID().toString();
            Calendar
                    cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.MINUTE, 10);
            Date expiryDate = cal.getTime();
            PasswordResetToken
                    passwordResetToken = new PasswordResetToken(token,user,expiryDate);




            passwordResetTokenServiceImpl.savePasswordResetToken(passwordResetToken);

            emailSender.sendPasswordResetToken(user.getEmail(), token);

            return ResponseEntity.ok().body("Email sent successfully");
        } else {
            return  ResponseEntity.ok().body(userOptional.toString());
            }
    }
    @Operation(
            summary = "Reset Password REST API",
            description = "Reset Password REST API is used to reset the password"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )

    @PutMapping("/change-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword){
        PasswordResetToken passwordResetToken = passwordResetTokenServiceImpl.findByToken(token);

        if (passwordResetToken == null && passwordResetToken.getExpiryDate().before(new Date())) {
      return new  ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        User user = passwordResetToken.getUser();


        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);


        userService.updateUserPassword(mapper.map(user, UserDto.class));


    return ResponseEntity.ok().body("Password reset successfully");}
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

