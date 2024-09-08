package net.saravana.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;


    @Column(name = "permissionValue")
    private int permissionValue;

    // One role can be assigned to many users
    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public Role(String name, int permissionValue) {
        super();
        this.name = name;
        this.permissionValue = permissionValue;
    }

    public Role(String name) {
        super();
        this.name = name;
        this.permissionValue = 0;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(int permissionValue) {
        this.permissionValue = permissionValue;
    }

    public Role() {

    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    // Utility methods for working with permissions
    public void addPermission(Permission permission) {
        this.permissionValue |= permission.getValue();  // Use bitwise OR to add permission
    }

    public void removePermission(Permission permission) {
        this.permissionValue &= ~permission.getValue();  // Use bitwise AND with NOT to remove permission
    }

    public boolean hasPermission(Permission permission) {
        return (this.permissionValue & permission.getValue()) != 0;  // Use bitwise AND to check permission
    }

}
