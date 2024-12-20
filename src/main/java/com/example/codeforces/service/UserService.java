package com.example.codeforces.service;

import com.example.codeforces.db.Role;
import com.example.codeforces.repository.RoleRepository;
import com.example.codeforces.db.User;
import com.example.codeforces.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        if (user.getRole() != null && roleRepository.existsById(user.getRole().getRoleId())) {
            return userRepository.save(user);
        }
        throw new IllegalArgumentException("Role not found or invalid user data.");
    }

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

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public User registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists.");
        }
        Role defaultRole = roleRepository.findByRoleName("USER")
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        user.setRole(defaultRole);
        user.setContestsAttended(0);
        user.setRegistrationDate(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
        return userRepository.save(user);
    }

    public Optional<User> login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().checkPassword(password)) {
            return user;
        }
        return Optional.empty();
    }
}
