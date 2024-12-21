package com.example.codeforces.controller;
//import com.example.codeforces.repository.UserContestRepository;
import com.example.codeforces.db.Contest;
import com.example.codeforces.db.User;
import com.example.codeforces.db.UserContest;
import com.example.codeforces.repository.UserContestRepository;
import com.example.codeforces.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private final UserContestRepository userContestRepository;

    public UserController(UserContestRepository userContestRepository) {
        this.userContestRepository = userContestRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User savedUser = userService.createUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/contests")
    public ResponseEntity<List<Contest>> getUserContests(Principal principal) {
        if (principal == null || principal.getName() == null) {
            return ResponseEntity.badRequest().build();
        }
        List<UserContest> contests = userContestRepository.findByUserUsername(principal.getName());

        if (contests.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contests.stream().map(UserContest::getContest).toList());
    }

//    @GetMapping("/contests/count")
//    public ResponseEntity<Integer> getUserContestsCount(Principal principal) {
//        if (principal == null || principal.getName() == null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        int contestsCount = userContestRepository.getContestCountByUsername(principal.getName());
//
//        return ResponseEntity.ok(contestsCount);
//    }
}
