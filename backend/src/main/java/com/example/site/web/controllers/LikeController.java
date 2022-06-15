package com.example.site.web.controllers;

import com.example.site.core.models.Like;
import com.example.site.core.services.LikeService;
import com.example.site.web.models.LikeInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/likes")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }
    @PostMapping(value = "/create")
    public Like createLike(@RequestBody LikeInput likeInput){
        return likeService.createLike(
                likeInput.user_id,
                likeInput.post_id,
                likeInput.comment_id
        );
    }

    @GetMapping(value = "/user/{id}/likes")
    public List<Like> getUserLikes(@PathVariable("id") Integer id)
    {
        return likeService.getUserLikes(id);
    }
    @GetMapping(value = "/post/{id}/likes")
    public List<Like> getPostLikes(@PathVariable("id") Integer id)
    {
        return likeService.getPostLikes(id);
    }
    @GetMapping(value = "/comment/{id}/likes")
    public List<Like> getCommentLikes(@PathVariable("id") Integer id)
    {
        return likeService.getCommentLikes(id);
    }


}
