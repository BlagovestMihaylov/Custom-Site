package com.example.site.web.models;

public class PostInput {
    public final Integer id;
    public final String title;
    public final String content;
    public final Integer user_id;
    public final Integer votes;
    public final Integer views;

    public PostInput(Integer id, String title, String content, Integer user_id, Integer votes, Integer views) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user_id = user_id;
        this.votes = votes;
        this.views = views;
    }
}
