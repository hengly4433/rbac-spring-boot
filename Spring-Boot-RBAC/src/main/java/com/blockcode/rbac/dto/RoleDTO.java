package com.blockcode.rbac.dto;

import com.blockcode.rbac.entity.Permission;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

public class RoleDTO {
    private Long id;

    @NotBlank(message = "Role name must not be blank")
    private String name;

    @NotEmpty(message = "At least one permission must be assigned")
    @Valid
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
