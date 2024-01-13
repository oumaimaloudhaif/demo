package com.example.demo.Services;

import com.example.demo.Dto.ReportDTO;
import com.example.demo.Entities.Report;

import java.util.List;

/**
 * Report Service
 */
public interface ReportService {
    List<ReportDTO> getAllReports();
    List<ReportDTO> searchReports(String keyword);

    Report addReport(Report report);

    Report updateReport(Report report) ;
}
