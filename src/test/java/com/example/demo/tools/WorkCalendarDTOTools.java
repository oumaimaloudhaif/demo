package com.example.demo.tools;

import com.example.demo.dto.WorkCalendarDTO;
import org.springframework.stereotype.Component;

/** Work Calendar DTO Tools */
@Component
public class WorkCalendarDTOTools {
  public static WorkCalendarDTO createWorkCalendarDTO(String tag) {

    return new WorkCalendarDTO().withTag(tag);
  }
}
