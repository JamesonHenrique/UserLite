package usermanagement.email;


import jakarta.persistence.*;
import usermanagement.entity.User;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Entity
public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;
    public PasswordResetToken() {
    }
    public PasswordResetToken(
            String token,
            User user, Date expiryDate) {
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User
            user;

    private Date
            expiryDate;





    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar
                cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public Long getId() {
        return id;
    }

    public void setId(
            Long id) {
        this.id =
                id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(
            String token) {
        this.token =
                token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(
            User user) {
        this.user =
                user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(
            Date expiryDate) {
        this.expiryDate =
                expiryDate;
    }
}