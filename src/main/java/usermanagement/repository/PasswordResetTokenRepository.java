package usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usermanagement.email.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);


}
