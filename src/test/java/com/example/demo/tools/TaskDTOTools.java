package com.example.demo.tools;

import com.example.demo.dto.TaskDTO;
import com.example.demo.enums.Priority;
import com.example.demo.enums.TaskStatus;
import org.springframework.stereotype.Component;

/** Task DTO Tools */
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
