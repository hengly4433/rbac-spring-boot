package com.blockcode.rbac.service;

import com.blockcode.rbac.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleDTO create(RoleDTO dto);
    RoleDTO update(Long id, RoleDTO dto);
    RoleDTO getById(Long id);
    List<RoleDTO> getAll();
    void delete(Long id);
}
