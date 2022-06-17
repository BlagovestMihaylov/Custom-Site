package com.example.site.repositories.models;

public class ReportDAO {
    public final Integer id;
    public final Integer user_id;
    public final Integer reported;
    public final String content;
    public final String report_date;


    public ReportDAO(Integer id, Integer user_id, Integer reported, String content, String report_date) {
        this.id = id;
        this.user_id = user_id;
        this.reported = reported;
        this.content = content;
        this.report_date = report_date;
    }
}
