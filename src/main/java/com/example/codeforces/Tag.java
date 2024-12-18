package com.example.codeforces;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    private String tagName;

    // Getters
    public Long getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    // Setters
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    // Constructors
    public Tag() {}

    public Tag(String tagName) {
        this.tagName = tagName;
    }
}
