package com.example.demo.controllers;

import com.example.demo.controllers.mappers.ReportMapper;
import com.example.demo.controllers.request.ReportRequest;
import com.example.demo.controllers.response.ReportResponse;
import com.example.demo.dto.ReportDTO;
import com.example.demo.entities.Report;
import com.example.demo.servicesImpl.ReportServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Report Controller */
@Validated
@RestController
public class ReportController {
  @Autowired private ReportServiceImpl reportServiceImpl;
  @Autowired private ReportMapper reportMapper;

  /**
   * Adds a new report
   *
   * @param report the report object to be added
   * @return ReportDTO
   */
  @PostMapping("/reports")
  public ReportDTO addReport(@RequestBody @Valid Report report) {
    return reportServiceImpl.addReport(report);
  }

  /**
   * Updates an existing report
   *
   * @param report the report object to be updated
   * @return ReportDTO
   */
  @PutMapping("/reports")
  public ReportDTO updateReport(@RequestBody @Valid Report report) {
    return reportServiceImpl.updateReport(report);
  }

  /**
   * @param reportRequest the request object containing the keyword related to the report
   * @return ReportResponse
   */
  @GetMapping("/reports")
  public ReportResponse getReports(
      @RequestParam(required = false) @Valid ReportRequest reportRequest) {
    if (reportRequest != null && reportRequest.getKeyword() != null) {
      return reportMapper.toReportResponse(
          reportServiceImpl.searchReports(reportRequest.getKeyword()));
    } else {
      return reportMapper.toReportResponse(reportServiceImpl.getAllReports());
    }
  }
}
