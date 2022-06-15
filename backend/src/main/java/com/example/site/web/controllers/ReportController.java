package com.example.site.web.controllers;

import com.example.site.core.models.Report;
import com.example.site.core.services.ReportService;
import com.example.site.web.models.ReportInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping(value = "/create")
    public Report createReport(@RequestBody ReportInput reportInput){
        return reportService.createReport(
                reportInput.user_id,
                reportInput.post_id,
                reportInput.comment_id,
                reportInput.content
        );
    }

    @GetMapping(value = "post/{id}/reports")
    public List<Report> getPostReport(@PathVariable("id") Integer id){
        return reportService.getPostReports(id);
    }
    @GetMapping(value = "user/{id}/reports")
    public List<Report> getUserReport(@PathVariable("id") Integer id){
        return reportService.getUserReports(id);
    }
    @GetMapping(value = "comment/{id}/reports")
    public List<Report> getCommentReport(@PathVariable("id") Integer id){
        return reportService.getCommentReports(id);
    }


}
