package usermanagement.service;

import usermanagement.email.PasswordResetToken;
import usermanagement.service.impl.PasswordResetTokenServiceImpl;

public interface PasswordResetTokenService {
    PasswordResetToken savePasswordResetToken(PasswordResetToken token);
    PasswordResetToken findByToken(String token);
}
