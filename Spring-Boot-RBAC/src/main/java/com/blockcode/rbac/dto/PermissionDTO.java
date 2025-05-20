package com.blockcode.rbac.dto;

import jakarta.validation.constraints.NotBlank;

public class PermissionDTO {
    private Long id;

    @NotBlank(message = "Permission name must not be blank")
    private  String name;

    public PermissionDTO() {
    }

    public PermissionDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
