package com.example.demo.Controllers;

import com.example.demo.Controllers.Mappers.ReportMapper;
import com.example.demo.Controllers.Request.ReportRequest;
import com.example.demo.Controllers.Response.ReportResponse;
import com.example.demo.Entities.Report;
import com.example.demo.ServicesImpl.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class ReportController {
    @Autowired
    private ReportServiceImpl reportServiceImpl;
    @Autowired
    private ReportMapper reportMapper;
    /**
     *
     * @return
     */
   /* @GetMapping("/reports")
    public ReportResponse getAllReports() {

        return reportMapper.toReportResponse(reportServiceImpl.getAllReports());
    }*/
    /**
     *
     *
     *@return Report
     */
    @PostMapping("/reports")
    public Report addReport(@RequestBody @Valid Report report) {
        return reportServiceImpl.addReport(report);
    }
    /**
     *
     *
     *@return Report
     */
    @PutMapping("/reports")
    public Report updateReport(@RequestBody @Valid Report report) {
        return reportServiceImpl.updateReport(report);
    }
    /**
     *
     * @return ReportResponse
     */
  /*  @GetMapping("/reports")
    public ReportResponse searchReport(@RequestParam(required = false) @Valid ReportRequest reportRequest) {
        return reportMapper.toReportResponse(reportServiceImpl.searchReports(reportRequest.getKeyword()));
    }*/

    @GetMapping("/reports")
    public ReportResponse getReports(@RequestParam(required = false) @Valid ReportRequest reportRequest) {
        if (reportRequest != null && reportRequest.getKeyword() != null) {
            return reportMapper.toReportResponse(reportServiceImpl.searchReports(reportRequest.getKeyword()));
        } else {
            return reportMapper.toReportResponse(reportServiceImpl.getAllReports());
        }
    }
}

