package com.example.site.repositories.mariaDB;

import com.example.site.repositories.models.CommentDAO;
import com.example.site.repositories.repos.CommentRepository;

import java.util.List;

public class MariaDBCommentRepository implements CommentRepository {
    @Override
    public CommentDAO createComment(int id, int user_id, int post_id, int comment_id, String content, int votes, String time) {
        return null;
    }

    @Override
    public List<CommentDAO> getPostComments(int post_id) {
        return null;
    }

    @Override
    public List<CommentDAO> getCommentComments(int comment_id) {
        return null;
    }


    public static class Queries {
        public static final String INSERT_POST_COMMENT = "";
        public static final String INSERT_COMMENT_COMMENT = "";
    }
}
