package usermanagement.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import usermanagement.entity.Role;
import usermanagement.entity.User;
import usermanagement.repository.RoleRepository;
import usermanagement.repository.UserRepository;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository
            userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(
            RoleRepository roleRepository,
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        // Verifique se roleAdmin é nulo e crie o Role se necessário
        if (roleAdmin == null) {
            roleAdmin = new Role();
            roleAdmin.setName(Role.Values.ADMIN.name());
            roleRepository.save(roleAdmin);
        }

        var userAdmin = userRepository.findByFirstNameAndLastName("admin","admin");

        Role
                finalRoleAdmin =
                roleAdmin;
        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("admin ja existe");
                },
                () -> {
                    var user = new User();
                    user.setFirstName("admin");
                    user.setLastName("admin");
                    user.setEmail("admin@admin");
                    user.setPassword(passwordEncoder.encode("123"));
                    user.setRoles(Set.of(
                            finalRoleAdmin));
                    userRepository.save(user);
                }
        );
    }
}