package usermanagement.repository;

import org.springframework.stereotype.Repository;
import usermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
@Repository

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
   Optional<User>  findByFirstNameAndLastName(String firstName, String lastName);
}
