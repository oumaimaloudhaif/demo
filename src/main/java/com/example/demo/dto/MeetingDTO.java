package com.example.demo.dto;

import java.time.LocalDateTime;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

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
