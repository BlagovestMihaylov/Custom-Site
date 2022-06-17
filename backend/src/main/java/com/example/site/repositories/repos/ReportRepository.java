package com.example.site.repositories.repos;

import com.example.site.repositories.models.ReportDAO;

import java.util.List;

public interface ReportRepository {
//    public ReportDAO createReport(
//            Integer user_id,
//            Integer post_id,
//            Integer comment_id,
//            String content
//    );

//    public List<ReportDAO> getUserReports(Integer user_id);

    ReportDAO reportComment(Integer user_id, Integer reported, String content);

    ReportDAO reportPost(Integer user_id, Integer reported, String content);

    ReportDAO reportUser(Integer user_id, Integer reported, String content);

    public List<ReportDAO> getReportedPosts(Integer post_id);
    public List<ReportDAO> getReportedComments(Integer comment_id);

    List<ReportDAO> getReportedUsers(Integer comment_id);
}
