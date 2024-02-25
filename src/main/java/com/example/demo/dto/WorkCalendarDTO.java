package com.example.demo.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.With;

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
