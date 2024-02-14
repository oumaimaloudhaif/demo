package com.example.demo.tools;

import com.example.demo.entities.Report;
import org.springframework.stereotype.Component;

/** Report Tools */
@Component
public class ReportTools {
  public static Report createReport(Long id, String title) {

    return new Report().withId(id).withTitle(title);
  }
}
