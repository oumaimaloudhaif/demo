package com.example.demo.controllers.mappers;

import com.example.demo.controllers.response.TaskResponse;
import com.example.demo.dto.TaskDTO;
import java.util.List;
import org.springframework.stereotype.Component;

/** Task Mapper */
@Component
public class TaskMapper {
  public TaskResponse toTasksResponse(List<TaskDTO> tasks) {
    TaskResponse tasksResponse = new TaskResponse();
    tasksResponse.setResult(tasks);

    return tasksResponse;
  }
}
