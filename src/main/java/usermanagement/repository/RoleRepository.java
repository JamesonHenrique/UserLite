package usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import usermanagement.entity.Role;


import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, Long> {
Role findByName(String name);
}
