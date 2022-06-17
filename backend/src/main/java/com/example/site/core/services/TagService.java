package com.example.site.core.services;

import com.example.site.core.models.Tag;
import com.example.site.repositories.repos.TagRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag createTag(String name) {
        return Mapper.fromTagDAO(tagRepository.createTag(name));
    }

    public List<Tag> getAllTags() {
        return tagRepository.getAllTags()
                .stream()
                .map(Mapper::fromTagDAO)
                .collect(Collectors.toList());
    }
}
