package com.example.demo.Dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@With
public class ReportDTO {
  private String title;
  private String content;

  public ReportDTO(String project) {
    this.title = project;
  }
}
