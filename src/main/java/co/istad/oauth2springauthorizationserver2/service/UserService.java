package co.istad.oauth2springauthorizationserver2.service;

import co.istad.oauth2springauthorizationserver2.dto.UserRegistrationRequest;
import co.istad.oauth2springauthorizationserver2.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

public interface UserService {


    void register(UserRegistrationRequest request);

}

