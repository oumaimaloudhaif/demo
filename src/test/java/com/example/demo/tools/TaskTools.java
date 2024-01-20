package com.example.demo.tools;

import com.example.demo.Entities.Task;
import com.example.demo.Enums.Priority;
import com.example.demo.Enums.TaskStatus;
import org.springframework.stereotype.Component;


@Component
public class TaskTools {
  public static Task createTask(Long id,String name, String description,TaskStatus taskStatus,Priority priority) {
    return new Task().withId_Task(id).withName(name).withDescription(description).withTaskStatus(taskStatus).withPriority(priority);
  }

}
