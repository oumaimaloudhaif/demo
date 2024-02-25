package com.example.demo.tools;

import com.example.demo.entities.WorkCalendar;
import org.springframework.stereotype.Component;

/** WorkCalendar Tools */
@Component
public class WorkCalendarTools {
  public static WorkCalendar createWorkCalendar(Long id, String tag) {

    return new WorkCalendar().withId_WorkCalendar(id).withTag(tag);
  }
}
