package com.example.site.core.models;

public class Like {
    public final Integer user_id;
    public final Integer liked_thing;
    public final String like_date;
    ;

    public Like( Integer user_id, Integer liked_thing, String like_date) {
        this.user_id = user_id;
        this.liked_thing = liked_thing;
        this.like_date = like_date;
    }
}
