package com.blockcode.rbac.service;

import com.blockcode.rbac.dto.UserDTO;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserDTO create(UserDTO dto);
    UserDTO update(Long id, UserDTO dto);
    UserDTO getById(Long id);
    List<UserDTO> getAll();
    void delete(Long id);
    UserDTO assignRoles(Long userId, Set<Long> roleIds);
}
