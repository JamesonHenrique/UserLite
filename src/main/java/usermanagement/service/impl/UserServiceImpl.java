package usermanagement.service.impl;

import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import usermanagement.dto.UserDto;
import usermanagement.entity.User;
import usermanagement.exception.EmailAlreadyExistsException;
import usermanagement.exception.ResourceNotFoundException;
import usermanagement.mapper.AutoUserMapper;
import usermanagement.repository.UserRepository;
import usermanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;
    public UserServiceImpl(UserRepository userRepository,ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        Optional.of(
                                "Objeto não encontrado"), User.class.getName()));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        Optional.of(
                                "Objeto não encontrado"), User.class.getName()));
        userRepository.deleteById(id);
    }

}
