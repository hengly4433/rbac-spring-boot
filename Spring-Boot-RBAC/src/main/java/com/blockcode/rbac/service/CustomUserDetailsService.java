package com.blockcode.rbac.service;

import com.blockcode.rbac.entity.Role;
import com.blockcode.rbac.entity.User;
import com.blockcode.rbac.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        List<GrantedAuthority> authorities  = user.getRoles().stream()
                .flatMap(this::mapRoleToAuthorities)
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }


    private Stream<GrantedAuthority> mapRoleToAuthorities(Role role) {
        // role itself
        GrantedAuthority roleAuth = new SimpleGrantedAuthority(role.getName());
        // plus each permission
        Stream<GrantedAuthority> permAuth = role.getPermissions().stream()
                .map(p -> new SimpleGrantedAuthority(p.getName()));
        return Stream.concat(Stream.of(roleAuth), permAuth);
    }
}
