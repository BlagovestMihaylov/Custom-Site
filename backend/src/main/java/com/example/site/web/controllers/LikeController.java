package com.example.site.web.controllers;

import com.example.site.core.models.Like;
import com.example.site.core.services.LikeService;
import com.example.site.web.models.LikeInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/like")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping(value = "/comment")
    public Like likeComment(@RequestBody LikeInput likeInput) {
        return likeService.likeComment(
                likeInput.user_id,
                likeInput.liked_thing
        );
    }

    @PostMapping(value = "/post")
    public Like likePost(@RequestBody LikeInput likeInput) {
        return likeService.likePost(
                likeInput.user_id,
                likeInput.liked_thing
        );
    }


//    @GetMapping(value = "/user/{id}/likes")
//    public List<Like> getUserLikes(@PathVariable("id") Integer id) {
//        return likeService.getUserLikes(id);
//    }

    @GetMapping(value = "/post/{id}/likes")
    public List<Like> getPostLikes(@PathVariable("id") Integer id) {
        return likeService.getPostLikes(id);
    }

    @GetMapping(value = "/comment/{id}/likes")
    public List<Like> getCommentLikes(@PathVariable("id") Integer id) {
        return likeService.getCommentLikes(id);
    }


}
