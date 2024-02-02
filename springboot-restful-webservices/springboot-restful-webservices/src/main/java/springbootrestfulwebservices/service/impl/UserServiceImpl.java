package springbootrestfulwebservices.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootrestfulwebservices.dto.UserDTO;
import springbootrestfulwebservices.entity.User;
import springbootrestfulwebservices.mapper.UserMapper;
import springbootrestfulwebservices.repository.UserRepository;
import springbootrestfulwebservices.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.mapToUser(userDTO);

        User savedUser = userRepository.save(user);

        UserDTO savedUserDTO = UserMapper.mapToUserDTO(savedUser);
        return savedUserDTO;

    }

    @Override
    public UserDTO getUserById(Long userId) {
       Optional<User> optionalUser = userRepository.findById(userId);
       User user = optionalUser.get();
       return UserMapper.mapToUserDTO(user);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);


    }
}
