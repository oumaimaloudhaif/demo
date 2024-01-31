package com.example.demo.Dto;

import java.time.LocalDateTime;
import lombok.*;

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
