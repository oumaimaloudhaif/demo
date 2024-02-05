package com.example.demo.Controllers;

import com.example.demo.Controllers.Mappers.TaskMapper;
import com.example.demo.Controllers.Request.TaskRequest;
import com.example.demo.Controllers.Response.TaskResponse;
import com.example.demo.dto.TaskDTO;
import com.example.demo.entities.Task;
import com.example.demo.ServicesImpl.TaskServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Validated
@RestController
public class TaskController {
  @Autowired private TaskServiceImpl taskServiceImpl;
  @Autowired private TaskMapper tasksMapper;

  /**
   *
   * @param task
   * @return TaskDTO
   */
  @PostMapping("/tasks")
  public TaskDTO addTask(@RequestBody @Valid Task task) {

    return taskServiceImpl.addTask(task);
  }

  /**
   *
   * @param task
   * @return TaskDTO
   */
  @PutMapping("/tasks")
  public TaskDTO updateTask(@RequestBody @Valid Task task) {
    return taskServiceImpl.updateTask(task);
  }

  /**
   *
   * @param taskRequest
   * @return TaskResponse
   */
  @GetMapping("/tasks")
  public TaskResponse getReports(@RequestParam(required = false) @Valid TaskRequest taskRequest) {
    if (taskRequest != null && taskRequest.getKeyword() != null) {
      return tasksMapper.toTasksResponse(taskServiceImpl.searchTasks(taskRequest.getKeyword()));
    } else {
      return tasksMapper.toTasksResponse(taskServiceImpl.getAllTasks());
    }
  }
}
