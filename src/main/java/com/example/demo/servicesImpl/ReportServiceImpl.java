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
          ReportDTO reportDTO = fromDOToDTO.MapReport(report);
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
          ReportDTO reportDTO = fromDOToDTO.MapReport(report);
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

    return fromDOToDTO.MapReport(savedReport);
  }

  /**
   * @param report the report object to be updated
   * @return ReportDTO
   */
  public ReportDTO updateReport(Report report) {
    final Report updateReport = reportRepository.save(report);

    return fromDOToDTO.MapReport(updateReport);
  }
}
