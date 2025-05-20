package com.blockcode.rbac.service;

import com.blockcode.rbac.dto.RoleDTO;
import com.blockcode.rbac.entity.Permission;
import com.blockcode.rbac.entity.Role;
import com.blockcode.rbac.exception.ResourceNotFoundException;
import com.blockcode.rbac.mapper.RoleMapper;
import com.blockcode.rbac.repository.PermissionRepository;
import com.blockcode.rbac.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleServiceImpl(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public RoleDTO create(RoleDTO dto) {
        if (roleRepository.findByName(dto.getName()).isPresent()) {
            throw new IllegalArgumentException("Role exists: " + dto.getName());
        }
        Role role = new Role();
        role.setName(dto.getName());
        Set<Permission> permissions = dto.getPermissions().stream()
                .map(pdto -> permissionRepository.findById(pdto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Permission not found: " + pdto.getName())))
                .collect(Collectors.toSet());
        role.setPermissions(permissions);
        return RoleMapper.toDTO(roleRepository.save(role));
    }

    @Override
    public RoleDTO update(Long id, RoleDTO dto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + id));
        role.setName(dto.getName());
        Set<Permission> permissions = dto.getPermissions().stream()
                .map(pdto -> permissionRepository.findById(pdto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Permission not found: " + pdto.getName())))
                .collect(Collectors.toSet());
        role.setPermissions(permissions);
        return RoleMapper.toDTO(roleRepository.save(role));
    }

    @Override
    public RoleDTO assignPermissions(Long roleId, Set<Long> permissionIds) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + roleId));
        Set<Permission> permissions = permissionIds.stream()
                .map(pid -> permissionRepository.findById(pid)
                        .orElseThrow(() -> new ResourceNotFoundException("Permission not found: " + pid)))
                .collect(Collectors.toSet());
        role.setPermissions(permissions);
        Role saved = roleRepository.save(role);
        return RoleMapper.toDTO(saved);
    }

    @Override
    public RoleDTO getById(Long id) {
        return roleRepository.findById(id)
                .map(RoleMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + id));
    }

    @Override
    public List<RoleDTO> getAll() {
        return roleRepository.findAll().stream()
                .map(RoleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Role role  = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + id));
        roleRepository.delete(role);
    }
}
