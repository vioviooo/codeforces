package com.example.codeforces.db;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Size(max = 15, message = "Username cannot exceed 15 characters")
    private String username;

    @Size(max = 50, message = "Email cannot exceed 50 characters")
    private String email;

    private String passwordHash;

    private Integer contestsAttended;
    private LocalDateTime registrationDate;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public User() {}

    public User(String username, String email, String password, Integer contestsAttended, LocalDateTime registrationDate, Role role) {
        this.username = username;
        this.email = email;
        setPassword(password);
        this.contestsAttended = contestsAttended;
        this.registrationDate = registrationDate;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getContestsAttended() {
        return contestsAttended;
    }

    public void setContestsAttended(Integer contestsAttended) {
        this.contestsAttended = contestsAttended;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPassword(String password) {
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password) {
        System.out.print("> PASSWORD: " + password + '\n');
        System.out.print("> DB PASSWORD HASH: " + this.passwordHash + '\n');
        return BCrypt.checkpw(password, this.passwordHash);
    }
}
// $2a$10$sq4YEUj6Vm14XQAcu1u7uu2JNCrxMZIoXYl3V5yha1LfVFFhILEny
// $2a$10$sq4YEUj6Vm14XQAcu1u7uu2JNCrxMZIoXYl3V5yha1LfVFFhILEny