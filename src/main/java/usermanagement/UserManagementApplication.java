package usermanagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;


@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot REST API Documentation",
				description = "Spring Boot REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Jameson Henrique",
						url = "https://www.linkedin.com/in/jamesonhenrique"

				)

		)

)
public class UserManagementApplication {



	public static void main(String[] args) {
		SpringApplication.run(
				UserManagementApplication.class, args);
	}

}
