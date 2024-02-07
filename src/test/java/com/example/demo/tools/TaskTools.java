package com.example.demo.tools;

import com.example.demo.entities.Task;
import com.example.demo.enums.Priority;
import com.example.demo.enums.TaskStatus;
import org.springframework.stereotype.Component;

/** Task Tools */
@Component
public class TaskTools {
  public static Task createTask(
      Long id, String name, String description, TaskStatus taskStatus, Priority priority) {

    return new Task()
        .withId_Task(id)
        .withName(name)
        .withDescription(description)
        .withTaskStatus(taskStatus)
        .withPriority(priority);
  }
}
