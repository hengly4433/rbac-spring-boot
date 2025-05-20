package com.blockcode.rbac.dto;

import com.blockcode.rbac.entity.Permission;

import java.util.HashSet;
import java.util.Set;

public class RoleDTO {
    private Long id;
    private String name;
    private Set<PermissionDTO> permissions = new HashSet<>();

    public RoleDTO() {
    }

    public RoleDTO(String name, Set<PermissionDTO> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionDTO> permissions) {
        this.permissions = permissions;
    }
}
