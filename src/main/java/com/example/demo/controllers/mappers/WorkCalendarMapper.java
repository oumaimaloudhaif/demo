package com.example.demo.controllers.mappers;

import com.example.demo.controllers.response.WorkCalendarResponse;
import com.example.demo.dto.WorkCalendarDTO;
import java.util.List;
import org.springframework.stereotype.Component;

/** WorkCalendarMapper */
@Component
public class WorkCalendarMapper {
  public WorkCalendarResponse toWorkCalendarResponse(List<WorkCalendarDTO> workCalendarDTOS) {
    WorkCalendarResponse workCalendarResponse = new WorkCalendarResponse();
    workCalendarResponse.setResult(workCalendarDTOS);

    return workCalendarResponse;
  }
}
