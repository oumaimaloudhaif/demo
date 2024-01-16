package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.ReportResponse;
import com.example.demo.Dto.ReportDTO;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {
  public ReportResponse toReportResponse(List<ReportDTO> reports) {
    ReportResponse reportsResponse = new ReportResponse();
    reportsResponse.setResult(reports);
    return reportsResponse;
  }
}
