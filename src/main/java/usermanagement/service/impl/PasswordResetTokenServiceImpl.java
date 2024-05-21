package usermanagement.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import usermanagement.email.PasswordResetToken;
import usermanagement.repository.PasswordResetTokenRepository;
import usermanagement.service.PasswordResetTokenService;
@Service
@AllArgsConstructor
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    private PasswordResetTokenRepository
            passwordResetTokenRepository;
    @Override
    public PasswordResetToken savePasswordResetToken(
            PasswordResetToken token) {
        return passwordResetTokenRepository.save(token);
    }

    @Override
    public PasswordResetToken findByToken(
            String token) {
        return passwordResetTokenRepository.findByToken(token);
    }
}
