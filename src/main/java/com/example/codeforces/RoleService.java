package com.example.codeforces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // Get all roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Create a new role
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // Get a role by ID (optional for this service, but useful in general)
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }
}
