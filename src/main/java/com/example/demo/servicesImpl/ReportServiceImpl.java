package com.example.demo.servicesImpl;

import com.example.demo.dto.ReportDTO;
import com.example.demo.dto.mappers.FromDOToDTO;
import com.example.demo.entities.Report;
import com.example.demo.repository.ReportRepository;
import com.example.demo.services.ReportService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Report Service Impl */
@Service
public class ReportServiceImpl implements ReportService {
  @Autowired private ReportRepository reportRepository;
  @Autowired private FromDOToDTO fromDOToDTO;

  /** @return List<ReportDTO> */
  public List<ReportDTO> getAllReports() {
    final List<Report> reports = reportRepository.findAll();
    List<ReportDTO> reportDTOS = new ArrayList<>();
    reports.forEach(
        report -> {
          ReportDTO reportDTO = fromDOToDTO.mapReport(report);
          reportDTOS.add(reportDTO);
        });

    return reportDTOS;
  }

  /**
   * @param keyword a keyword (report title) to search for reports
   * @return List<ReportDTO>
   */
  public List<ReportDTO> searchReports(String keyword) {
    final List<Report> reports = reportRepository.findByTitle(keyword);
    List<ReportDTO> reportDTOS = new ArrayList<>();
    reports.forEach(
        report -> {
          ReportDTO reportDTO = fromDOToDTO.mapReport(report);
          reportDTOS.add(reportDTO);
        });

    return reportDTOS;
  }

  /**
   * @param report the report object to be added
   * @return ReportDTO
   */
  public ReportDTO addReport(Report report) {
    final Report savedReport = reportRepository.save(report);

    return fromDOToDTO.mapReport(savedReport);
  }

  /**
   * @param report the report object to be updated
   * @return ReportDTO
   */
  public ReportDTO updateReport(Report report) {
    final Report updateReport = reportRepository.save(report);

    return fromDOToDTO.mapReport(updateReport);
  }

  /**
   * Retrieves a report by its ID.
   *
   * @param projectId the ID of the project to retrieve
   * @return the ReportDTO corresponding to the report, or null if the report does not exist
   */
  @Override
  public ReportDTO getReportById(Long projectId) {
    final Report report = reportRepository.findById(projectId).orElse(null);
    if (report != null) {

      return fromDOToDTO.mapReport(report);
    } else {

      return null;
    }
  }

  /**
   * Deletes a report by its ID.
   *
   * @param reportId the ID of the report to delete
   * @return true if the report was deleted successfully, false otherwise
   */
  @Override
  public boolean deleteReportById(Long reportId) {
    final Report report = reportRepository.findById(reportId).orElse(null);
    if (report != null) {
      reportRepository.delete(report);

      return true;
    } else {

      return false;
    }
  }
}
