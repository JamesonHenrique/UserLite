package usermanagement.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
    private JavaMailSender javaMailSender;
    public EmailSender(
            JavaMailSender javaMailSender) {
        this.javaMailSender =
                javaMailSender;
    }
    public void sendPasswordResetToken(
            String to,
            String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Password Reset Token");
        message.setText("Use this token to reset your password: " + token );
        javaMailSender.send(message);
    }
}
