package com.example.codeforces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Create a new user
    public User createUser(User user) {
        if (user.getRole() != null && roleRepository.existsById(user.getRole().getRoleId())) {
            return userRepository.save(user);
        }
        throw new IllegalArgumentException("Role not found or invalid user data.");
    }

    // Update an existing user
    public Optional<User> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setContestsAttended(updatedUser.getContestsAttended());
            existingUser.setRegistrationDate(updatedUser.getRegistrationDate());
            existingUser.setRole(updatedUser.getRole());
            return userRepository.save(existingUser);
        });
    }

    // Delete a user
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
