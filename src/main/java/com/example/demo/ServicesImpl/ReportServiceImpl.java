package com.example.demo.ServicesImpl;

import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Dto.ReportDTO;
import com.example.demo.Entities.Report;
import com.example.demo.Repository.ReportRepository;
import com.example.demo.Services.ReportService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Report Service Impl
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private FromDOToDTO fromDOToDTO;

    /**
     * @return all Reports
     */
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

    public ReportDTO addReport(Report report) {
        final Report savedReport = reportRepository.save(report);
        return fromDOToDTO.MapReport(savedReport);
    }

    public ReportDTO updateReport(Report report) {
        final Report updateReport = reportRepository.save(report);
        return fromDOToDTO.MapReport(updateReport);
    }
}
