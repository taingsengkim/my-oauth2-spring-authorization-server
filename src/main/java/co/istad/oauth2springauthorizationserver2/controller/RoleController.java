
package co.istad.oauth2springauthorizationserver2.controller;

import co.istad.oauth2springauthorizationserver2.dto.CreateRoleRequest;
import co.istad.oauth2springauthorizationserver2.entity.Role;
import co.istad.oauth2springauthorizationserver2.service.RoleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/roles")
public class RoleController {

    private final RoleServiceImpl roleServiceImpl;

    public RoleController(RoleServiceImpl roleServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role createRole(@RequestBody CreateRoleRequest request) {
        return roleServiceImpl.createRole(request.name(), request.description()
        );
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleServiceImpl.getAllRoles();
    }


}
