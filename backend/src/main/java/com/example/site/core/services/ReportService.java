package com.example.site.core.services;

import com.example.site.core.models.Report;
import com.example.site.repositories.repos.ReportRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ReportService {

    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

//    public Report createReport(Integer user_id, Integer post_id, Integer comment_id, String content) {
//        return Mapper.fromReportDAO(repository.createReport(
//                user_id,
//                post_id,
//                comment_id,
//                content));
//    }

    public List<Report> getPostReports(Integer id) {
        return repository.getReportedPosts(id)
                .stream()
                .map(Mapper::fromReportDAO)
                .collect(Collectors.toList());
    }

//    public List<Report> getUserReports(Integer id) {
//        return repository.getUserReports(id)
//                .stream()
//                .map(Mapper::fromReportDAO)
//                .collect(Collectors.toList());
//    }

    public List<Report> getCommentReports(Integer id) {
        return repository.getReportedComments(id)
                .stream()
                .map(Mapper::fromReportDAO)
                .collect(Collectors.toList());
    }

    public Report reportPost(Integer user_id, Integer reported, String content) {
        return Mapper.fromReportDAO(repository.reportPost(user_id, reported, content));
    }

    public Report reportUser(Integer user_id, Integer reported, String content) {
        return Mapper.fromReportDAO(repository.reportUser(user_id, reported, content));
    }

    public Report reportComment(Integer user_id, Integer reported, String content) {
        return Mapper.fromReportDAO(repository.reportComment(user_id, reported, content));
    }
}
