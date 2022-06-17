package com.example.site.core.services;

import com.example.site.core.models.Category;
import com.example.site.core.models.Post;
import com.example.site.core.models.Tag;
import com.example.site.repositories.models.CategoryDAO;
import com.example.site.repositories.repos.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.site.core.services.Mapper.fromPostDAO;

public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(String title,
                           String content,
                           int user_id,
                           int votes,
                           int views) {
        return fromPostDAO(postRepository.createPost(title,
                content,
                user_id,
                votes,
                views));
    }

    public Post getPostById(int id) {
        return fromPostDAO(postRepository.getPostById(id));
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


    public String getUserName(int id) {
        return postRepository.getUserName(id);
    }

    public String getUserImageUrl(int id) {
        return postRepository.getUserImageUlr(id);
    }

    public List<Tag> getPostTags(Integer id) {
        return (postRepository.getPostTags(id))
                .stream()
                .map(Mapper::fromTagDAO)
                .collect(Collectors.toList());
    }


    public List<Category> getPostCategory(Integer id) {
        return (postRepository.getPostCategories(id))
                .stream()
                .map(Mapper::fromCategoryDAO)
                .collect(Collectors.toList());
    }

    public Integer getLikesCount(Integer id) {
        return postRepository.getPostVotes(id);
    }

    public void addCategory(Integer post_id, Integer cat_id) {
        postRepository.addCategory(post_id, cat_id);
    }

    public void addTag(Integer post_id, Integer tag_id) {
        postRepository.addTag(post_id, tag_id);
    }
}
