package usermanagement.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        Resource resource = new ClassPathResource("data.sql");
        String sql = new String(Files.readAllBytes(Paths.get(resource.getURI())));

        // Split the SQL string into multiple statements
        String[] sqlStatements = sql.split(";");

        // Execute each SQL statement
        Arrays.stream(sqlStatements)
                .map(String::trim)  // Remove leading and trailing whitespaces
                .filter(statement -> !statement.isEmpty())  // Ignore empty lines
                .forEach(jdbcTemplate::execute);
    }
}