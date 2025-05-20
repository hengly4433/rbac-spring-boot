package com.blockcode.rbac.controller;

import com.blockcode.rbac.dto.RoleDTO;
import com.blockcode.rbac.service.RoleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PreAuthorize("hasAuthority('VIEW_ROLE')")
    @GetMapping
    public List<RoleDTO> list() { return roleService.getAll(); }

    @PreAuthorize("hasAuthority('VIEW_ROLE')")
    @GetMapping("/{id}")
    public RoleDTO get(@PathVariable Long id) { return roleService.getById(id); }

    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    @PostMapping
    public ResponseEntity<RoleDTO> create(@Valid @RequestBody RoleDTO dto) {
        return new ResponseEntity<>(roleService.create(dto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('EDIT_ROLE')")
    @PutMapping("/{id}")
    public RoleDTO update(@PathVariable Long id, @Valid @RequestBody RoleDTO dto) {
        return roleService.update(id, dto);
    }

    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ASSIGN_PERMISSION')")
    @PostMapping("/{id}/permissions")
    public ResponseEntity<RoleDTO> assignPermissions(
            @PathVariable("id") Long roleId,
            @RequestBody
            @NotEmpty(message = "permissionIds must not be empty")
            Set<@NotNull(message = "permissionId must not be null") Long> permissionIds) {
        RoleDTO updated = roleService.assignPermissions(roleId, permissionIds);
        return ResponseEntity.ok(updated);
    }
}
