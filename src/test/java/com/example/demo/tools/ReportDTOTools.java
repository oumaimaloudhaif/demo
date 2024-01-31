package com.example.demo.tools;

import com.example.demo.Dto.ReportDTO;
import org.springframework.stereotype.Component;

@Component
public class ReportDTOTools {
  public static ReportDTO createReportDTO(String title) {
    return new ReportDTO().withTitle(title);
  }
}
