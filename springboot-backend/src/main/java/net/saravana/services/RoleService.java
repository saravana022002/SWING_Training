package net.saravana.services;

import net.saravana.entities.Employee;
import net.saravana.entities.Permission;
import net.saravana.entities.Role;
import net.saravana.exceptions.ResourceNotFoundException;
import net.saravana.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles(){
        return  roleRepository.findAll();
    }

    public Role createRole(Role role){
        return roleRepository.save(role);
    }

    public Role getRoleById(Long id){
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
    }
    public void deleteRole(Role role){
        roleRepository.delete(role);
    }

    public Role addPermissionsToRole(Long roleId, List<Permission> permissions) {
        Role role = getRoleById(roleId);
        role.setPermissionValue(0);
        for (Permission permission : permissions) {
            role.addPermission(permission);
        }
        return roleRepository.save(role);
    }

    public boolean hasPermission(Long roleId, Permission permission) {
        Role role = getRoleById(roleId);
        return role.hasPermission(permission);
    }
}
