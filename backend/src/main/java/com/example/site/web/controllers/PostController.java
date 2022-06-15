package com.example.site.web.controllers;

import com.example.site.core.models.Post;
import com.example.site.core.services.PostService;
import com.example.site.web.models.PostInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping(value = "/create")
    public Post createPost(@RequestBody PostInput postInput) {
        return postService.createPost(postInput.title,
                postInput.content,
                postInput.user_id,
                postInput.votes,
                postInput.views
        );
    }


    @GetMapping
    public List<Post> listPost(
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        return postService.listPosts(page, pageSize);
    }

    @GetMapping(value = "/{id}")
    public Post getPost(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
    }

    @GetMapping(value = "/{id}/username")
    public String getPostUserUsername(@PathVariable("id") Integer id) {
        return postService.getUserName(id);
    }

    @GetMapping(value = "/{id}/image")
    public String getPostUserImageUrl(@PathVariable("id") Integer id) {
        return postService.getUserImageUrl(id);
    }
}



