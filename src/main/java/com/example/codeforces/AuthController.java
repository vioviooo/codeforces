package com.example.codeforces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user);
            return ResponseEntity.ok(newUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Map<String, String> credentials) {
        System.out.println("Login attempt: " + credentials + '\n');
        System.out.print(BCrypt.hashpw("42", BCrypt.gensalt()));
        Optional<User> user = userService.login(credentials.get("username"), credentials.get("password"));
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.status(401).build());
    }
}

// $2a$10$LRy0CghA7A9siHRdDwHGl.47A1OXA1vnDAMbSqAQ8jJLtujvRNggC
// $2a$10$sq4YEUj6Vm14XQAcu1u7uu2JNCrxMZIoXYl3V5yha1LfVFFhILEny
// $2a$10$S2tUY8HPm/WShhPkBaazRedh8.IL3xN6bVZl2wadDUUeu1c0NVHGC
