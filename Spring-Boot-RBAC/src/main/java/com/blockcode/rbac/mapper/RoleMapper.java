package com.blockcode.rbac.mapper;

import com.blockcode.rbac.dto.RoleDTO;
import com.blockcode.rbac.entity.Role;

import java.util.stream.Collectors;


public class RoleMapper {
    public static RoleDTO toDTO(Role r) {
        RoleDTO dto = new RoleDTO();
        dto.setId(r.getId());
        dto.setName(r.getName());
        dto.setPermissions(
                r.getPermissions().stream()
                        .map(PermissionMapper::toDTO)
                        .collect(Collectors.toSet())
        );
        return dto;
    }
}
