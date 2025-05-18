package com.blockcode.rbac.mapper;

import com.blockcode.rbac.dto.UserDTO;
import com.blockcode.rbac.entity.User;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toDTO(User u) {
        UserDTO dto = new UserDTO();
        dto.setId(u.getId());
        dto.setUsername(u.getUsername());
        dto.setEmail(u.getEmail());
        dto.setRoles(
                u.getRoles().stream()
                        .map(RoleMapper::toDTO)
                        .collect(Collectors.toSet())
        );
        return dto;
    }
}
