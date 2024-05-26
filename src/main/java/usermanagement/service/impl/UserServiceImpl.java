package usermanagement.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import usermanagement.dto.UserDto;
import usermanagement.dto.UserResponseDto;
import usermanagement.email.PasswordResetToken;
import usermanagement.entity.Role;
import usermanagement.entity.User;
import usermanagement.exception.EmailAlreadyExistsException;
import usermanagement.repository.RoleRepository;
import usermanagement.repository.UserRepository;
import usermanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;
    private BCryptPasswordEncoder passwordEncoder;
    private RoleRepository
            roleRepository;
    public UserServiceImpl(UserRepository userRepository,ModelMapper modelMapper,BCryptPasswordEncoder passwordEncoder,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
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
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email já cadastrado");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role
                basicRole = roleRepository.findByName("basic");
        if (basicRole != null) {

            user.setRoles(new HashSet<>(
                    Arrays.asList(basicRole)));
        }

        return userRepository.save(user);
    }

    @Override
    public User updateUser(
            UserDto userDto) {
        User existingUser = modelMapper.map(userDto, User.class);
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(existingUser.getPassword());

return userRepository.save(existingUser);
    }
    @Override
    public User updateUserPassword(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return userRepository.save(user);
    }
    @Override
    public void deleteUser(Long id) {
       User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        Optional.of(
                                "Objeto não encontrado"), User.class.getName()));

        Set<Role>
                roles = user.getRoles();
roles.clear();

        userRepository.deleteById(id);
    }

    public Optional<User> findByEmail(
            String email) {
        return userRepository.findByEmail(email);

    }


}
