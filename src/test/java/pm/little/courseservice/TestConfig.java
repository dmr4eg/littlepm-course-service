package pm.little.courseservice;

import org.mockito.Mockito;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Optional;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableWebMvc
@EnableTransactionManagement
@EnableConfigurationProperties
@ComponentScan(basePackages = {"pm.little.api", "pm.little.courseservice"})
@EntityScan(basePackages = "pm.little.api.models")
public class TestConfig {
    // This empty class provides the Spring Boot configuration needed for tests
    
    /**
     * Override bean for repositories that need to handle null return values safely
     */
    @Bean
    @Primary
    public <T> Optional<T> createOptionalBean() {
        return Optional.empty();
    }
    
    /**
     * Provides a named bean for NativeWebRequest to avoid conflicts
     */
    @Bean(name = "nativeWebRequest")
    public NativeWebRequest nativeWebRequest() {
        return Mockito.mock(NativeWebRequest.class);
    }
} 