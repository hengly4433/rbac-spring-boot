package com.blockcode.rbac.service;

import com.blockcode.rbac.dto.UserDTO;
import com.blockcode.rbac.entity.Role;
import com.blockcode.rbac.entity.User;
import com.blockcode.rbac.exception.ResourceNotFoundException;
import com.blockcode.rbac.mapper.UserMapper;
import com.blockcode.rbac.repository.RoleRepository;
import com.blockcode.rbac.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService  {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDTO create(UserDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        User u = new User();
        u.setUsername(dto.getUsername());
        u.setEmail(dto.getEmail());
        u.setPassword(encoder.encode(dto.getPassword()));
        Set<Role> roles = dto.getRoles().stream()
                .map(rdto -> roleRepository.findById(rdto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Role not found")))
                .collect(Collectors.toSet());
        u.setRoles(roles);
        return UserMapper.toDTO(userRepository.save(u));
    }

    @Override
    public UserDTO update(Long id, UserDTO dto) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
        if (!u.getUsername().equals(dto.getUsername())
            && userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username token: " + dto.getUsername());
        }
        u.setUsername(dto.getUsername());
        u.setEmail(dto.getEmail());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            u.setPassword(encoder.encode(dto.getPassword()));
        }
        Set<Role> roles = dto.getRoles().stream()
                .map(rdto -> roleRepository.findById(rdto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + rdto.getName())))
                .collect(Collectors.toSet());
        u.setRoles(roles);
        return UserMapper.toDTO(userRepository.save(u));
    }

    @Override
    public UserDTO getById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
        userRepository.delete(u);
    }
}
