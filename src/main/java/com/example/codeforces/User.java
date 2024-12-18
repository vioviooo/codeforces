package com.example.codeforces;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String email;
    private Integer contestsAttended;
    private LocalDateTime registrationDate;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    // Constructors
    public User() {}

    public User(String username, String email, Integer contestsAttended, LocalDateTime registrationDate, Role role) {
        this.username = username;
        this.email = email;
        this.contestsAttended = contestsAttended;
        this.registrationDate = registrationDate;
        this.role = role;
    }

    // Getters and Setters
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
}