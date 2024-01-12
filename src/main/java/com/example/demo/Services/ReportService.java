package com.example.demo.Services;

import com.example.demo.Entities.Report;

import java.util.List;

/**
 * Report Service
 */
public interface ReportService {
    List<Report> getAllReports();
    List<Report> searchReports(String keyword);

    Report addReport(Report report);

    Report updateReport(Report report) ;
}
