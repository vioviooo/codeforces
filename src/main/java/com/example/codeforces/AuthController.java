package com.example.codeforces;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.Principal;
import java.time.LocalDateTime;
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
        System.out.println("Login attempt: " + credentials + '\n');
        System.out.print(BCrypt.hashpw("42", BCrypt.gensalt()));

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

//    @PostMapping("/register")
//    public String postRegisterPage(HttpServletRequest request, User user, Model model, Principal principal) {
//        if (principal != null) {
//            return "redirect:/home";
//        }
//
//        user.setPassword(user.getPasswordHash()); // Hash the password inside the entity
//        user.setRegistrationDate(LocalDateTime.now());
//        user.setRole(user.getRole().findByRoleName("USER")); // Assign default role, e.g., "USER"
//
//        userRepository.save(user);
//
//        UsernamePasswordAuthenticationToken authRequest =
//                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPasswordHash());
//
//        Authentication authentication = authenticationManager.authenticate(authRequest);
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        securityContext.setAuthentication(authentication);
//
//        HttpSession session = request.getSession(true);
//        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
//
//        return "login.html";
//    }
}

// $2a$10$LRy0CghA7A9siHRdDwHGl.47A1OXA1vnDAMbSqAQ8jJLtujvRNggC
// $2a$10$sq4YEUj6Vm14XQAcu1u7uu2JNCrxMZIoXYl3V5yha1LfVFFhILEny
// $2a$10$S2tUY8HPm/WShhPkBaazRedh8.IL3xN6bVZl2wadDUUeu1c0NVHGC
