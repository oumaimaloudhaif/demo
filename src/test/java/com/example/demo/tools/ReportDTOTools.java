package com.example.demo.tools;

import com.example.demo.dto.ReportDTO;
import org.springframework.stereotype.Component;

/** Report DTO Tools */
@Component
public class ReportDTOTools {
  public static ReportDTO createReportDTO(String title) {

    return new ReportDTO().withTitle(title);
  }
}
