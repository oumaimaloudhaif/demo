package com.example.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

/** Meeting DTO */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@With
public class MeetingDTO {
  private String title;

  private LocalDateTime startTime;

  public MeetingDTO(String meeting) {
    this.title = meeting;
  }
}
