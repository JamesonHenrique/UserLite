package usermanagement.service.impl;

import lombok.AllArgsConstructor;
import usermanagement.dto.UserDto;
import usermanagement.entity.User;
import usermanagement.exception.ResourceNotFoundException;
import usermanagement.mapper.AutoUserMapper;
import usermanagement.repository.UserRepository;
import usermanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;



    @Override
    public UserDto createUser(UserDto userDto) {



        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);


        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User optionalUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","id",userId));

        User user = optionalUser;

        return AutoUserMapper.MAPPER.mapToUserDto(optionalUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();


        return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                ( )-> new ResourceNotFoundException("User","id",user.getId())

        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);

        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {

        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","id",userId));
        userRepository.deleteById(userId);
    }
}
