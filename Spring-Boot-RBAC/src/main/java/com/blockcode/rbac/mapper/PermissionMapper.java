package com.blockcode.rbac.mapper;

import com.blockcode.rbac.dto.PermissionDTO;
import com.blockcode.rbac.entity.Permission;

public class PermissionMapper {
    public static PermissionDTO toDTO(Permission p) {
        PermissionDTO dto = new PermissionDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        return  dto;
    }
}
