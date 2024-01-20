package com.example.demo.Dto;

import com.example.demo.Enums.Priority;
import com.example.demo.Enums.TaskStatus;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@With
public class TaskDTO {
  private String name;
  private String description;

  @Enumerated(EnumType.STRING)
  private Priority priority;

  @Enumerated(EnumType.STRING)
  private TaskStatus taskStatus;
}
