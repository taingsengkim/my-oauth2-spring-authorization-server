
package co.istad.oauth2springauthorizationserver2.service;

import co.istad.oauth2springauthorizationserver2.dto.UserRegistrationRequest;
import co.istad.oauth2springauthorizationserver2.entity.User;
import co.istad.oauth2springauthorizationserver2.repository.RoleRepository;
import co.istad.oauth2springauthorizationserver2.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl  implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(UserRegistrationRequest request) {
        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        var defaultRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Default USER role not found"));

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.getRoles().add(defaultRole);
        user.setEnabled(true);

        userRepository.save(user);
    }
}
