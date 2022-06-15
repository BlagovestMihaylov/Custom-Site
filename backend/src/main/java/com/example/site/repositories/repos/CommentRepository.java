package com.example.site.repositories.repos;

import com.example.site.repositories.models.CommentDAO;
import com.example.site.repositories.models.PostDAO;

import java.util.List;

public interface CommentRepository {
    CommentDAO createComment(
            Integer user_id, Integer post_id, Integer comment_id,
            String content, Integer votes, String time
    );

    List<CommentDAO> getPostComments(int post_id);

    List<CommentDAO> getCommentComments(int comment_id);
}
