package com.example.demo.tools;

import com.example.demo.Dto.TaskDTO;
import com.example.demo.Enums.Priority;
import com.example.demo.Enums.TaskStatus;
import org.springframework.stereotype.Component;

@Component
public class TaskDTOTools {
  public static TaskDTO createTaskDTO(
      String name, String description, TaskStatus taskStatus, Priority priority) {
    return new TaskDTO()
        .withName(name)
        .withDescription(description)
        .withTaskStatus(taskStatus)
        .withPriority(priority);
  }
}
