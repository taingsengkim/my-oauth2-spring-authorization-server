
package co.istad.oauth2springauthorizationserver2.controller;

import co.istad.oauth2springauthorizationserver2.dto.CreateRoleRequest;
import co.istad.oauth2springauthorizationserver2.entity.Role;
import co.istad.oauth2springauthorizationserver2.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role createRole(@RequestBody CreateRoleRequest request) {
        return roleService.createRole(request.name(), request.description()
        );
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }


}
