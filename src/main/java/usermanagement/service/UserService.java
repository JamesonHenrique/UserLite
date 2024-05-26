package usermanagement.service;

import usermanagement.dto.UserDto;
import usermanagement.dto.UserResponseDto;
import usermanagement.email.PasswordResetToken;
import usermanagement.entity.User;

import java.util.List;

public interface UserService {
    User createUser(UserDto user);

    User getUserById(Long userId);

    List<User> getAllUsers();

    User updateUser(
            UserDto user);
    User updateUserPassword(
            UserDto user);

    void deleteUser(Long userId);

}
