package com.example.site.web.models;

public class ReportInput {
    public final Integer id;
    public final Integer user_id;
    public final Integer post_id;
    public final Integer comment_id;
    public final String content;
    public final String report_date;

    public ReportInput(Integer id, Integer user_id, Integer post_id, Integer comment_id, String content, String report_date) {
        this.id = id;
        this.user_id = user_id;
        this.post_id = post_id;
        this.comment_id = comment_id;
        this.content = content;
        this.report_date = report_date;
    }
}
