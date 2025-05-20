package com.blockcode.rbac.service;

import com.blockcode.rbac.dto.RoleDTO;

import java.util.List;
import java.util.Set;

public interface RoleService {
    RoleDTO create(RoleDTO dto);
    RoleDTO update(Long id, RoleDTO dto);
    RoleDTO getById(Long id);
    List<RoleDTO> getAll();
    void delete(Long id);
    RoleDTO assignPermissions(Long roleId, Set<Long> permissionIds);
}
