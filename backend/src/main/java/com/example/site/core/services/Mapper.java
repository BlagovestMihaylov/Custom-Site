package com.example.site.core.services;

import com.example.site.core.models.Friendship;
import com.example.site.core.models.Post;
import com.example.site.core.models.User;
import com.example.site.repositories.models.PostDAO;
import com.example.site.repositories.models.UserDAO;

public class Mapper {
    public static User fromUserDAO(UserDAO userDAO) {
        return new User(userDAO.id,
                userDAO.username,
                userDAO.email,
                userDAO.password,
                userDAO.phone_number,
                userDAO.registration_date,
                userDAO.image_url);
    }

    public static Post fromPostDAO(PostDAO postDAO) {
        return new Post(postDAO.id,
                postDAO.title,
                postDAO.content,
                postDAO.user_id,
                postDAO.votes,
                postDAO.views);
    }

    public static Friendship fromFriendshipDAO(Friendship friendship) {
        return new Friendship(
                friendship.user1_id,
                friendship.user2_id
        );
    }
}
