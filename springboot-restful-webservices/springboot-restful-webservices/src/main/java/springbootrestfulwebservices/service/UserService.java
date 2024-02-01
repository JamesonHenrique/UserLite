package springbootrestfulwebservices.service;

import springbootrestfulwebservices.entity.User;

public interface UserService {
    User createUser(User user);
    User getUserById(Long userId);
}
