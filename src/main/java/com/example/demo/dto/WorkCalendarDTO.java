package com.example.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** WorkCalendar DTO */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkCalendarDTO {
  LocalDateTime startTime;

  LocalDateTime endTime;
}
