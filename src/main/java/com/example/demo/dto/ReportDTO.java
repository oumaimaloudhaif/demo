package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;
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
