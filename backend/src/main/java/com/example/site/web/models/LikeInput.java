package com.example.site.web.models;

public class LikeInput {
    public final Integer id;
    public final Integer user_id;
    public final Integer post_id;
    public final Integer comment_id;
    public final String like_date;;

    public LikeInput(Integer id, Integer user_id, Integer post_id, Integer comment_id, String like_date) {
        this.id = id;
        this.user_id = user_id;
        this.post_id = post_id;
        this.comment_id = comment_id;
        this.like_date = like_date;
    }
}
