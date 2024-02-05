package com.example.demo.dto;

import com.example.demo.enums.Priority;
import com.example.demo.enums.TaskStatus;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class TaskDTO {
  private String name;

  private String description;

  @Enumerated(EnumType.STRING)
  private Priority priority;

  @Enumerated(EnumType.STRING)
  private TaskStatus taskStatus;
}
