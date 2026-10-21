
package co.istad.oauth2springauthorizationserver2.controller;

import co.istad.oauth2springauthorizationserver2.dto.UserRegistrationRequest;
import co.istad.oauth2springauthorizationserver2.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> register(
            @Valid @RequestBody UserRegistrationRequest request) {

        userServiceImpl.register(request);

        return Map.of("message", "User registered successfully");
    }
}
