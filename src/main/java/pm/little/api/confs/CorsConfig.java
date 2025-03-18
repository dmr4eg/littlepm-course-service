package pm.little.api.confs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("*")  // Allow all origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")  // Allowed HTTP methods
                .allowedHeaders("*");  // Allow all headers

    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }
}
