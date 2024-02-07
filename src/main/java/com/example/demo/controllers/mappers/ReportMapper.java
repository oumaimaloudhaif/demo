package com.example.demo.controllers.mappers;

import com.example.demo.controllers.response.ReportResponse;
import com.example.demo.dto.ReportDTO;
import java.util.List;
import org.springframework.stereotype.Component;

/** Report Mapper */
@Component
public class ReportMapper {
  public ReportResponse toReportResponse(List<ReportDTO> reports) {
    ReportResponse reportsResponse = new ReportResponse();
    reportsResponse.setResult(reports);

    return reportsResponse;
  }
}
