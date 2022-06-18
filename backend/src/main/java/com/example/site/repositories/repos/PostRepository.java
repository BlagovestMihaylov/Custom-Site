package com.example.site.repositories.repos;

import com.example.site.repositories.models.CategoryDAO;
import com.example.site.repositories.models.PostDAO;
import com.example.site.repositories.models.TagDAO;
import org.springframework.data.relational.core.sql.In;

import java.util.Arrays;
import java.util.List;

public interface PostRepository {
    PostDAO createPost(
            String title,
            String content,
            int user_id,
            int votes,
            int views);

    PostDAO getPostById(Integer id);

    String getUserImageUrlByUserId(Integer id);

    String getUserNameByUserId(Integer id);


    List<PostDAO> getPostsByUserId(Integer user_id);

    List<PostDAO> listPosts(int page, int pageSize);

    void deletePost(Integer id);

    String getUserName(int id);

//    Arrays getPostsByUserId(int user_id);

    String getUserImageUlr(int id);

    List<TagDAO> getPostTags(Integer post_id);

    List<CategoryDAO> getPostCategories(Integer post_id);

    Integer getPostVotes(Integer post_id);



    void addCategory(Integer post_id, Integer category_id);
    void addTag(Integer post_id, Integer tag_id);

    List<PostDAO> getPosts();
}

