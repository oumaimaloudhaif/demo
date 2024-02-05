package com.example.demo.Controllers;

import com.example.demo.Controllers.Mappers.ReportMapper;
import com.example.demo.Controllers.Request.ReportRequest;
import com.example.demo.Controllers.Response.ReportResponse;
import com.example.demo.dto.ReportDTO;
import com.example.demo.entities.Report;
import com.example.demo.ServicesImpl.ReportServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
@RestController
public class ReportController {
  @Autowired private ReportServiceImpl reportServiceImpl;
  @Autowired private ReportMapper reportMapper;

  /**
   *
   * @param report
   * @return ReportDTO
   */

  @PostMapping("/reports")
  public ReportDTO addReport(@RequestBody @Valid Report report) {
    return reportServiceImpl.addReport(report);
  }

  /**
   *
   * @param report
   * @return ReportDTO
   */
  @PutMapping("/reports")
  public ReportDTO updateReport(@RequestBody @Valid Report report) {
    return reportServiceImpl.updateReport(report);
  }

  /**
   *
   * @param reportRequest
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
