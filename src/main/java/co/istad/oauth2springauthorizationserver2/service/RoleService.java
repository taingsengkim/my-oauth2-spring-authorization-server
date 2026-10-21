package co.istad.oauth2springauthorizationserver2.service;

import co.istad.oauth2springauthorizationserver2.entity.Role;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface RoleService {

    Role createRole(String name, String description) ;
    List<Role> getAllRoles();
}
