package com.example.demo.services;

import com.example.demo.dto.ReportDTO;
import com.example.demo.entities.Report;
import java.util.List;

/** Report Service */
public interface ReportService {
  List<ReportDTO> getAllReports();

  List<ReportDTO> searchReports(String keyword);

  ReportDTO addReport(Report report);

  ReportDTO updateReport(Report report);

  ReportDTO getReportById(Long projectId);

  boolean deleteReportById(Long reportId);
}
