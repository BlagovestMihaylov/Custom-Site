package com.example.site.core.models;

public class Followship {
    public final Integer user1_id;
    public final Integer user2_id;

    public Followship(Integer user1_id, Integer user2_id) {
        this.user1_id = user1_id;
        this.user2_id = user2_id;
    }
}
