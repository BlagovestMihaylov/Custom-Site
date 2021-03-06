package com.example.site.core.services;

import com.example.site.core.models.*;
import com.example.site.repositories.models.*;

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
                postDAO.views,
                postDAO.date);
    }

    public static Followship fromFriendshipDAO(Followship friendship) {
        return new Followship(
                friendship.user1_id,
                friendship.user2_id
        );
    }

    public static Report fromReportDAO(ReportDAO reportDAO) {
        return new Report(
                reportDAO.id,
                reportDAO.user_id,
                reportDAO.reported,
                reportDAO.content,
                reportDAO.report_date
        );
    }

    public static Like fromLikeDAO(LikeDAO likeDAO) {
        return new Like(
                likeDAO.user_id,
                likeDAO.liked_thing,
                likeDAO.like_date
        );
    }

    public static Comment fromCommentDAO(CommentDAO commentDAO) {
        return new Comment(commentDAO.id,
                commentDAO.user_id,
                commentDAO.post_id,
                commentDAO.comment_id,
                commentDAO.content,
                commentDAO.votes,
                commentDAO.date);
    }

    public static Tag fromTagDAO(TagDAO tagDAO) {
        return new Tag(tagDAO.id, tagDAO.name);
    }

    public static Category fromCategoryDAO(CategoryDAO categoryDAO) {
        return new Category(categoryDAO.id, categoryDAO.name);

    }
}
