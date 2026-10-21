
package co.istad.oauth2springauthorizationserver2.service;

import co.istad.oauth2springauthorizationserver2.entity.Role;
import co.istad.oauth2springauthorizationserver2.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Role createRole(String name, String description) {
        String normalizedName = name.trim().toUpperCase();

        if (roleRepository.findByName(normalizedName).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role already exists");
        }

        Role role = new Role();
        role.setName(normalizedName);
        role.setDescription(description);

        return roleRepository.save(role);
    }

    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
