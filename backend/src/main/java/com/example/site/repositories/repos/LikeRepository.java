package com.example.site.repositories.repos;

import com.example.site.repositories.models.LikeDAO;

import java.util.List;

public interface LikeRepository {
    public LikeDAO createLike(
            Integer user_id,
            Integer post_id,
            Integer comment_id
    );

    public List<LikeDAO> getPostLikes(Integer post_id);

    public List<LikeDAO> getUserLikes(Integer user_id);

    public List<LikeDAO> getCommentLikes(Integer comment_id);
}
