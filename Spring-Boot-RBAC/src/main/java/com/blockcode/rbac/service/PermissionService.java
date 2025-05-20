package com.blockcode.rbac.service;

import com.blockcode.rbac.dto.PermissionDTO;

import java.util.List;

public interface PermissionService {
    PermissionDTO create(PermissionDTO dto);
    PermissionDTO update(Long id, PermissionDTO dto);
    PermissionDTO getById(Long id);
    List<PermissionDTO> getAll();
    void delete(Long id);
}
