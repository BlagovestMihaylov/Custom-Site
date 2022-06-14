package com.example.site.repositories.models;

public class CommentDAO {
    public final Integer id;
    public final Integer user_id;
    public final Integer post_id;
    public final Integer comment_id;
    public final String content;
    public final Integer votes;
    public final String date;


    public CommentDAO(Integer id,
                      Integer user_id,
                      Integer post_id,
                      Integer comment_id,
                      String content,
                      Integer votes,
                      String date) {
        this.id = id;
        this.user_id = user_id;
        this.post_id = post_id;
        this.comment_id = comment_id;
        this.content = content;
        this.votes = votes;
        this.date = date;
    }
}
