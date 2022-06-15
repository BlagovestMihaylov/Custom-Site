package com.example.site.core.services;

import com.example.site.core.models.Like;
import com.example.site.repositories.repos.LikeRepository;

import java.util.List;
import java.util.stream.Collectors;

public class LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public Like createLike(Integer user_id, Integer post_id, Integer comment_id) {
        return Mapper.fromLikeDAO(likeRepository.createLike(
                user_id,
                post_id,
                comment_id
        ));
    }

    public List<Like> getUserLikes(Integer id) {
        return likeRepository.getUserLikes(id).stream()
                .map(Mapper::fromLikeDAO)
                .collect(Collectors.toList());
    }
    public List<Like> getPostLikes(Integer id) {
        return likeRepository.getPostLikes(id).stream()
                .map(Mapper::fromLikeDAO)
                .collect(Collectors.toList());
    }
    public List<Like> getCommentLikes(Integer id) {
        return likeRepository.getCommentLikes(id).stream()
                .map(Mapper::fromLikeDAO)
                .collect(Collectors.toList());
    }
}
