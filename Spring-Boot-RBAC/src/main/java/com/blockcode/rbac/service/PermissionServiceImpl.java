package com.blockcode.rbac.service;

import com.blockcode.rbac.dto.PermissionDTO;
import com.blockcode.rbac.entity.Permission;
import com.blockcode.rbac.exception.ResourceNotFoundException;
import com.blockcode.rbac.mapper.PermissionMapper;
import com.blockcode.rbac.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public PermissionDTO create(PermissionDTO dto) {
        if (permissionRepository.findByName(dto.getName()).isPresent()) {
            throw new IllegalArgumentException("Permission exists: " + dto.getName());
        }
        Permission p = new Permission(dto.getName());
        return PermissionMapper.toDTO(permissionRepository.save(p));
    }

    @Override
    public PermissionDTO update(Long id, PermissionDTO dto) {
        Permission p = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found: " + id));
        p.setName(dto.getName());
        return PermissionMapper.toDTO(permissionRepository.save(p));
    }

    @Override
    public PermissionDTO getById(Long id) {
        return permissionRepository.findById(id)
                .map(PermissionMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found: " + id));
    }

    @Override
    public List<PermissionDTO> getAll() {
        return permissionRepository.findAll().stream()
                .map(PermissionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Permission p = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found: " + id));
        permissionRepository.delete(p);
    }
}
