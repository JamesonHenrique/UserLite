package springbootrestfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootrestfulwebservices.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
