package com.example.demo.controllers.response;

import com.example.demo.dto.WorkCalendarDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** WorkCalendar Response */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkCalendarResponse {
  List<WorkCalendarDTO> result;
}
