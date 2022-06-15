package com.example.site.repositories.repos;

import com.example.site.repositories.models.ReportDAO;

import java.util.List;

public interface ReportRepository {
    public ReportDAO createReport(
            Integer user_id,
            Integer post_id,
            Integer comment_id,
            String content
    );

    public List<ReportDAO> getUserReports(Integer user_id);
    public List<ReportDAO> getPostReports(Integer post_id);
    public List<ReportDAO> getCommentReports(Integer comment_id);

}
