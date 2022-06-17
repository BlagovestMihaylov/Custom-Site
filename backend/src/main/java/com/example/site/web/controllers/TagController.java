package com.example.site.web.controllers;

import com.example.site.core.models.Tag;
import com.example.site.core.services.TagService;
import com.example.site.web.models.TagInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tag")
public class TagController {
    public final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping(value = "/create")
    public Tag createTag(@RequestBody TagInput tagInput) {
        return tagService.createTag(tagInput.name);
    }

    @GetMapping()
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }
}
