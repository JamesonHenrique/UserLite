package usermanagement.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import usermanagement.dto.UserDto;
import usermanagement.email.PasswordResetToken;
import usermanagement.entity.Role;
import usermanagement.entity.User;
import usermanagement.exception.EmailAlreadyExistsException;
import usermanagement.repository.RoleRepository;
import usermanagement.repository.UserRepository;
import usermanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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

    public Optional<User> findByEmail(
            String email) {
        return userRepository.findByEmail(email);

    }


}
