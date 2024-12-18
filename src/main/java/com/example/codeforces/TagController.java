package com.example.codeforces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    // Create a new tag
    @PostMapping
    public ResponseEntity<Tag> addTag(@RequestBody Tag tag) {
        Tag savedTag = tagService.createTag(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTag);
    }

    // Get all tags
    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }

    // Get a tag by ID
    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
        return tagService.getTagById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Update a tag
    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody Tag updatedTag) {
        return tagService.updateTag(id, updatedTag)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete a tag
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        if (tagService.deleteTag(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
