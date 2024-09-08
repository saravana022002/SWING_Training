package net.saravana.controllers;

import lombok.RequiredArgsConstructor;
import net.saravana.entities.Permission;
import net.saravana.entities.Role;
import net.saravana.repositories.RoleRepository;
import net.saravana.services.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    private final RoleService roleService;

    // get all employees
    @GetMapping("/all")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @PostMapping("/create")
    public Role createRole(@RequestParam Role role) {
        return roleService.createRole(role);
    }

    @PostMapping("/{roleId}/addPermissions")
    public Role addPermission(@PathVariable Long roleId, @RequestParam List<Permission> permissions) {
        return roleService.addPermissionsToRole(roleId, permissions);
    }
    @GetMapping("/{roleId}/hasPermission")
    public boolean hasPermission(@PathVariable Long roleId, @RequestParam Permission permission) {
        return roleService.hasPermission(roleId, permission);
    }
}
