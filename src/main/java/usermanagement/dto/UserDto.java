package usermanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Schema(
        description = "UserDto Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @Schema(
            description = "User id"
    )
    private Long id;
    @NotEmpty
    @Schema(
            description = "User first name"
    )
    private String firstName;
    @NotEmpty
    @Schema(
            description = "User last name"
    )
    private String lastName;
    @NotEmpty
    @Schema(
            description = "User email address"
    )
    @Email
    private String email;
    @NotEmpty
    @Schema(
            description = "Password email address"
    )
    private String password;
}
