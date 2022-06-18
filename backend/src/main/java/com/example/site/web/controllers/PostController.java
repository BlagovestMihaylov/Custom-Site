package com.example.site.web.controllers;

import com.example.site.core.models.Category;
import com.example.site.core.models.Post;
import com.example.site.core.models.Tag;
import com.example.site.core.services.PostService;
import com.example.site.web.models.PostInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/posts")
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
                0,
                0
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

    @GetMapping(value = "/{id}/tags")
    public List<Tag> getPostTags(@PathVariable("id") Integer id){
        return  postService.getPostTags(id);
    }
    @GetMapping(value = "/{id}/categories")
    public List<Category> getPostCategory(@PathVariable("id") Integer id){
        return  postService.getPostCategory(id);
    }

    @GetMapping(value = "/{id}/likes-count")
    public Integer getLikesCount(@PathVariable("id") Integer id){
        return postService.getLikesCount(id);
    }

    @PostMapping(value = "/add-cat/{post_id}/{cat_id}")
    public void addCategory(@PathVariable("post_id") Integer post_id, @PathVariable("cat_id") Integer cat_id){
         postService.addCategory(post_id, cat_id);
    }
    @PostMapping(value = "/add-tag/{post_id}/{tag_id}")
    public void addTag(@PathVariable("post_id") Integer post_id, @PathVariable("tag_id") Integer tag_id){
         postService.addTag(post_id, tag_id);
    }

}



