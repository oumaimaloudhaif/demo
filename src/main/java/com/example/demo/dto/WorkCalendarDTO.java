package com.example.demo.dto;

import java.time.LocalDateTime;
import lombok.*;

/** WorkCalendar DTO */
@Setter
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
public class WorkCalendarDTO {
  LocalDateTime startTime;
  LocalDateTime endTime;
  String tag;

  public WorkCalendarDTO(String tag) {
    this.tag = tag;
  }
}
