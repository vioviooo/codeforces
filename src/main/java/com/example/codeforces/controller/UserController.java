package com.example.codeforces.controller;
//import com.example.codeforces.repository.UserContestRepository;
import com.example.codeforces.db.*;
import com.example.codeforces.service.*;
import com.example.codeforces.repository.UserArchiveRepository;
import com.example.codeforces.repository.UserContestRepository;
import com.example.codeforces.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private final BackupService backupService;

    private final UserContestRepository userContestRepository;

    private final UserArchiveRepository userArchiveRepository;

    public UserController(UserContestRepository userContestRepository, UserArchiveRepository userArchiveRepository, BackupService backupService) {
        this.userContestRepository = userContestRepository;
        this.userArchiveRepository = userArchiveRepository;
        this.backupService = backupService;
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

    @GetMapping("/archive-problems")
    public ResponseEntity<List<ArchiveProblem>> getUserArchive(Principal principal) {
        if (principal == null || principal.getName() == null) {
            return ResponseEntity.badRequest().build();
        }
        List<UserProblem> contests = userArchiveRepository.findByUserUsername(principal.getName());

        if (contests.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contests.stream().map(UserProblem::getProblem).toList());
    }

    @GetMapping("/admin/backup")
    public void loadBackup(HttpServletResponse response) {
        String backupPath = backupService.getBackupPath();

        response.setHeader("Content-disposition", "attachment;filename=" + backupPath);
        response.setContentType("application/octet-stream");

        try {
            backupService.loadBackup(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @PostMapping("/admin/backup")
//    public ResponseEntity<String> uploadBackup(@RequestParam("backupFile") MultipartFile backupFile) {
//        if (backupFile.isEmpty()) {
//            return ResponseEntity.badRequest().body("Backup file is empty.");
//        }
//
//        try {
//            backupService.restoreFromBackupFile(backupFile);
//            return ResponseEntity.ok("Backup restored successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to restore backup: " + e.getMessage());
//        }
//    }
@PostMapping("/admin/backup")
public ResponseEntity<Map<String, String>> uploadBackup(@RequestParam("backupFile") MultipartFile backupFile) {
    if (backupFile.isEmpty()) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Backup file is empty.");
        return ResponseEntity.badRequest().body(response);
    }

    try {
        backupService.restoreFromBackupFile(backupFile);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Backup restored successfully.");
        return ResponseEntity.ok(response);
    } catch (Exception e) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Failed to restore backup: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

}
