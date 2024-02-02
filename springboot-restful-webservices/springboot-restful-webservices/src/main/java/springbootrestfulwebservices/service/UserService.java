package springbootrestfulwebservices.service;

import springbootrestfulwebservices.dto.UserDTO;
import springbootrestfulwebservices.entity.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);
    UserDTO getUserById(Long userId);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(Long userId);
}
