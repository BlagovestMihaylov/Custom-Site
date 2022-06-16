package com.example.site.repositories.repos;

import com.example.site.repositories.models.PostDAO;
import com.example.site.repositories.models.UserDAO;

import java.util.List;

public interface UserRepository {
    UserDAO createUser(
            String username, String email,
            String password, String phone_number, String image_url);

    UserDAO getUserById(Integer id);
    UserDAO getUserByUsername(String userName);

    List<UserDAO> listUsers(int page, int pageSize);

    void deleteUser(Integer id);

    void makeFollow(Integer myId, Integer friendId);
    void  makeUnfollow(Integer myId, Integer friendId);

    List<UserDAO> getFollowers(int id);

    List<UserDAO> getFollowings(int id);

    List<PostDAO> getUserPosts(int id);
}
