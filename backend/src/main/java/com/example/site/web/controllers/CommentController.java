package com.example.site.web.controllers;


import com.example.site.core.models.Comment;
import com.example.site.core.services.CommentService;
import com.example.site.web.models.CommentInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/create")
    public Comment createComment(@RequestBody CommentInput commentInput) {
        return commentService.createComment(
                commentInput.user_id,
                commentInput.post_id,
                commentInput.comment_id,
                commentInput.content,
                commentInput.votes ==
                        null ? 0 : commentInput.votes,
                commentInput.date
        );
    }

    @GetMapping(value = "/post/{postId}/comments")
    public List<Comment> getPostComments(@PathVariable("postId") Integer post_id){
        return commentService.getPostComments(post_id);
    }
    @GetMapping(value = "/comment/{commentId}/comments")
    public List<Comment> getCommentComments(@PathVariable("commentId") Integer comment_Id){
        return  commentService.getCommentComments(comment_Id);
    }
}
