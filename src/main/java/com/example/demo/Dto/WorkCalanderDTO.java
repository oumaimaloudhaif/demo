package com.example.demo.Dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkCalanderDTO {
  LocalDateTime startTime;
  LocalDateTime endTime;
}
