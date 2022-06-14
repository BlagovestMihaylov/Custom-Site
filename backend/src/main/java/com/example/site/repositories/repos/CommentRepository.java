package com.example.site.repositories.repos;

import com.example.site.repositories.models.CommentDAO;
import com.example.site.repositories.models.PostDAO;

import java.util.List;

public interface CommentRepository {
    CommentDAO createComment(
            int id, int user_id, int post_id, int comment_id,
            String content, int votes, String time
    );

    List<CommentDAO> getPostComments(int post_id);

    List<CommentDAO> getCommentComments(int comment_id);
}
