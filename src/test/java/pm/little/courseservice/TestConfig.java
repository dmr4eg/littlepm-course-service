package pm.little.courseservice;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"pm.little.api", "pm.little.courseservice"})
@EnableJpaRepositories(basePackages = "pm.little.api.repositories")
@EntityScan(basePackages = "pm.little.api.models")
public class TestConfig {
    // This empty class provides the Spring Boot configuration needed for tests
} 