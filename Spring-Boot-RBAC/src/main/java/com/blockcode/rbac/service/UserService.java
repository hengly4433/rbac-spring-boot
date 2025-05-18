package com.blockcode.rbac.service;

import com.blockcode.rbac.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO create(UserDTO dto);
    UserDTO update(Long id, UserDTO dto);
    UserDTO getById(Long id);
    List<UserDTO> getAll();
    void delete(Long id);
}
