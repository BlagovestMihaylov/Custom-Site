package com.example.site.web.models;

public class FollowshipInput {
    public final Integer user1_id;
    public final Integer user2_id;

    public FollowshipInput(Integer user1_id, Integer user2_id) {
        this.user1_id = user1_id;
        this.user2_id = user2_id;
    }
}
