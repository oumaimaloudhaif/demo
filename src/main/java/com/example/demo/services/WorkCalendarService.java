package com.example.demo.services;

import com.example.demo.dto.WorkCalendarDTO;
import com.example.demo.entities.WorkCalendar;
import java.util.List;

/** WorkCalendar Service */
public interface WorkCalendarService {
  List<WorkCalendarDTO> searchWorkCalendars(String keyword);

  WorkCalendarDTO addWorkCalendar(WorkCalendar workCalendar);

  WorkCalendarDTO updateWorkCalendar(WorkCalendar workCalendar);

  List<WorkCalendarDTO> getAllWorkCalendars();

  WorkCalendarDTO getWorkCalendarById(Long taskId);

  boolean deleteWorkCalendarById(Long taskId);
}
