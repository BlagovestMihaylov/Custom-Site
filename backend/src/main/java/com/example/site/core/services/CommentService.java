package com.example.site.core.services;

import com.example.site.core.models.Comment;
import com.example.site.repositories.repos.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.site.core.services.Mapper.fromCommentDAO;

public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(
            Integer user_id,
            Integer post_id,
            Integer comment_id,
            String content,
            Integer votes,
            String date) {
        return fromCommentDAO(commentRepository.createComment(
                user_id,
                post_id,
                comment_id,
                content,
                votes,
                date
        ));
    }

    public List<Comment> getPostComments(Integer post_id) {
        return commentRepository.getPostComments(post_id)
                .stream()
                .map(Mapper::fromCommentDAO)
                .collect(Collectors.toList());
    }

    public List<Comment> getCommentComments(Integer comment_id) {
        return commentRepository.getCommentComments(comment_id)
                .stream()
                .map(Mapper::fromCommentDAO)
                .collect(Collectors.toList());
    }
}
