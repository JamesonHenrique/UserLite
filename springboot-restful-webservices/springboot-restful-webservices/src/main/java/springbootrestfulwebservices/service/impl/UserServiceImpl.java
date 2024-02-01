package springbootrestfulwebservices.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootrestfulwebservices.entity.User;
import springbootrestfulwebservices.repository.UserRepository;
import springbootrestfulwebservices.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
