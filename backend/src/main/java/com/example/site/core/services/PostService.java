package com.example.site.core.services;

import com.example.site.core.models.Post;
import com.example.site.repositories.repos.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(String title,
                           String content,
                           int user_id,
                           int votes,
                           int views,
                           String date) {
        return Mapper.fromPostDAO(postRepository.createPost(title,
                content,
                user_id,
                votes,
                views));
    }

    public Post getPostById(int id) {
        return Mapper.fromPostDAO(postRepository.getPostById(id));
    }

//    public List<Post> getPostsByUserId(int user_id) {
//        return postRepository.getPostsByUserId(user_id)
//                .stream()
//                .map(Mapper::fromPostDAO)
//                .collect(Collectors.toList());
//    }

    public List<Post> listPosts(int page, int pageSize) {
        return postRepository.listPosts(page, pageSize)
                .stream()
                .map(Mapper::fromPostDAO)
                .collect(Collectors.toList());
    }

    public void deletePost(int id) {
        postRepository.deletePost(id);
    }


    public String getUserName(int id){
        return postRepository.getUserName(id);
    }
    public String getUserImageUrl(int id){
        return postRepository.getUserImageUlr(id);
    }
}
