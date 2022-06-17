package com.example.site.web.controllers;

import com.example.site.core.models.Report;
import com.example.site.core.services.ReportService;
import com.example.site.web.models.ReportInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "api/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping(value = "/comment")
    public Report reportComment(@RequestBody ReportInput reportInput){
        return reportService.reportComment(
                reportInput.user_id,
                reportInput.reported,
                reportInput.content
        );
    }
    @PostMapping(value = "/user")
    public Report reportUser(@RequestBody ReportInput reportInput){
        return reportService.reportUser(
                reportInput.user_id,
                reportInput.reported,
                reportInput.content
        );
    }
    @PostMapping(value = "/post")
    public Report reportPost(@RequestBody ReportInput reportInput){
        return reportService.reportPost(
                reportInput.user_id,
                reportInput.reported,
                reportInput.content
        );
    }

    @GetMapping(value = "post/{id}")
    public List<Report> getPostReport(@PathVariable("id") Integer id){
        return reportService.getPostReports(id);
    }
//    @GetMapping(value = "user/{id}/reports")
//    public List<Report> getUserReport(@PathVariable("id") Integer id){
//        return reportService.getUserReports(id);
//    }
    @GetMapping(value = "comment/{id}")
    public List<Report> getCommentReport(@PathVariable("id") Integer id){
        return reportService.getCommentReports(id);
    }


}
