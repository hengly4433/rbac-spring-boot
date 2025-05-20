package com.blockcode.rbac.controller;

import com.blockcode.rbac.dto.UserDTO;
import com.blockcode.rbac.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PreAuthorize("hasAuthority('VIEW_USER')")
    @GetMapping()
    public List<UserDTO> list() {
        return  userService.getAll();
    }

//    @PreAuthorize("hasAuthority('CREATE_USER')")
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
        return new ResponseEntity<>(userService.create(dto), HttpStatus.CREATED);
    }

//    @PreAuthorize("hasAuthority('EDIT_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO dto) {
        return new ResponseEntity<>(userService.update(id, dto), HttpStatus.OK);
    }

//    @PreAuthorize("hasAuthority('DELETE_USER')")
    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
