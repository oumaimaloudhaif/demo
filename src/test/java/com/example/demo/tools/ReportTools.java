package com.example.demo.tools;

import com.example.demo.Entities.Report;
import org.springframework.stereotype.Component;


@Component
public class ReportTools {
  public static Report createReport(Long id, String title) {
    return new Report().withId(id).withTitle(title);
  }

}
