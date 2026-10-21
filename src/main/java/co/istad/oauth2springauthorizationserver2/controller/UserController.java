
package co.istad.oauth2springauthorizationserver2.controller;

import co.istad.oauth2springauthorizationserver2.dto.UserRegistrationRequest;
import co.istad.oauth2springauthorizationserver2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> register(
            @Valid @RequestBody UserRegistrationRequest request) {

        userService.register(request);

        return Map.of("message", "User registered successfully");
    }
}
