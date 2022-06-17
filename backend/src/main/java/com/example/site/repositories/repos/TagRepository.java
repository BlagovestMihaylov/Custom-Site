package com.example.site.repositories.repos;

import com.example.site.repositories.models.TagDAO;

import java.util.List;

public interface TagRepository {
    TagDAO createTag(String name);
    List<TagDAO> getAllTags();
}
