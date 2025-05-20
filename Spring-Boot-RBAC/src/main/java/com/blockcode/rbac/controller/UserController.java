package com.blockcode.rbac.controller;

import com.blockcode.rbac.dto.UserDTO;
import com.blockcode.rbac.service.UserService;
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
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('VIEW_USER')")
    @GetMapping()
    public List<UserDTO> list() {
        return  userService.getAll();
    }

    @PreAuthorize("hasAuthority('CREATE_USER')")
    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) {
        return new ResponseEntity<>(userService.create(dto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('EDIT_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
        return new ResponseEntity<>(userService.update(id, dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DELETE_USER')")
    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ASSIGN_ROLE')")
    @PostMapping("/{id}/roles")
    public ResponseEntity<UserDTO> assignRoles(
            @PathVariable("id") Long userId,
            @RequestBody
            @NotEmpty(message = "roleIds must not be empty")
            Set<@NotNull(message = "roleId must not be null") Long> roleIds) {
        UserDTO updated = userService.assignRoles(userId, roleIds);
        return ResponseEntity.ok(updated);
    }
}
