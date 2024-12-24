package com.example.codeforces.controller;

import com.example.codeforces.db.User;
import com.example.codeforces.repository.UserRepository;
import com.example.codeforces.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public ResponseEntity<User> login(HttpServletRequest request, @RequestBody Map<String, String> credentials) {

        Optional<User> user = userService.login(credentials.get("username"), credentials.get("password"));

        if (user.isEmpty()) {return ResponseEntity.status(401).build();}

        UsernamePasswordAuthenticationToken authRequest =
            new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password"));

        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        log.info("INFO: {}", securityContext.getAuthentication().isAuthenticated());

        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        return user.map(ResponseEntity::ok).orElse(ResponseEntity.status(401).build());
    }

    @GetMapping("/user")
    public ResponseEntity<Map<String, Object>> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return ResponseEntity.status(401).build();  // Unauthorized response if not authenticated
        }

        String username = authentication.getName();  // Get the username from authentication context

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(404).build();  // Return 404 if user not found
        }

        User user = userOptional.get();

        Integer contestsCount = jdbcTemplate.queryForObject(
                "SELECT count_user_contests(?)", Integer.class, username);

        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("contestsCount", contestsCount);

        return ResponseEntity.ok(response);  // Return the response with user info and contest count
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().build();
    }
}