package co.istad.oauth2springauthorizationserver2.config;



import co.istad.oauth2springauthorizationserver2.entity.User;
import co.istad.oauth2springauthorizationserver2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserBootstrapConfig {

    @Bean
    CommandLineRunner createTestUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        return args -> {
            if (userRepository.findByUsername("demo-user").isPresent()) {
                return;
            }

            User user = new User();
            user.setUsername("demo-user");
            user.setPassword(passwordEncoder.encode("password123"));
            user.setRole("USER");
            user.setEnabled(true);

            userRepository.save(user);

            System.out.println("Test user created: demo-user");
        };
    }
}
