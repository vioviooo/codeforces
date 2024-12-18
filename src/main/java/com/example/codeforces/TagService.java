package com.example.codeforces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    // Get all tags
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    // Get a tag by ID
    public Optional<Tag> getTagById(Long id) {
        return tagRepository.findById(id);
    }

    // Create a new tag
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    // Update a tag
    public Optional<Tag> updateTag(Long id, Tag updatedTag) {
        return tagRepository.findById(id).map(existingTag -> {
            existingTag.setTagName(updatedTag.getTagName());
            return tagRepository.save(existingTag);
        });
    }

    // Delete a tag
    public boolean deleteTag(Long id) {
        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
