package com.blockcode.rbac.controller;

import com.blockcode.rbac.dto.PermissionDTO;
import com.blockcode.rbac.service.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

//    @PreAuthorize("hasAuthority('VIEW_PERMISSION')")
    @GetMapping
    public List<PermissionDTO> list() { return  permissionService.getAll(); }

//    @PreAuthorize("hasAuthority('VIEW_PERMISSION')")
    @GetMapping("/{id}")
    public PermissionDTO get(@PathVariable Long id) { return  permissionService.getById(id);  }

//    @PreAuthorize("hasAuthority('CRATE_PERMISSION')")
    @PostMapping
    public ResponseEntity<PermissionDTO> create(@RequestBody PermissionDTO dto) {
        return new ResponseEntity<>(permissionService.create(dto), HttpStatus.CREATED);
    }

//    @PreAuthorize("hasAuthority('EDIT_PERMISSION')")
    @PutMapping("/{id}")
    public PermissionDTO update(@PathVariable Long id, @RequestBody PermissionDTO dto) {
        return permissionService.update(id, dto);
    }

//    @PreAuthorize("hasAuthority('DELETE_PERMISSION')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        permissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
