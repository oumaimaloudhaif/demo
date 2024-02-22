package com.example.demo.controllers;

import com.example.demo.controllers.mappers.TaskMapper;
import com.example.demo.controllers.request.TaskRequest;
import com.example.demo.controllers.response.TaskResponse;
import com.example.demo.dto.TaskDTO;
import com.example.demo.entities.Task;
import com.example.demo.servicesImpl.TaskServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Task Controller */
@Validated
@RestController
public class TaskController {
  @Autowired private TaskServiceImpl taskServiceImpl;
  @Autowired private TaskMapper tasksMapper;

  /**
   * Adds a new task
   *
   * @param task the task object to be added
   * @return TaskDTO
   */
  @PostMapping("/tasks")
  public TaskDTO addTask(@RequestBody @Valid Task task) {

    return taskServiceImpl.addTask(task);
  }

  /**
   * Updates an existing task
   *
   * @param task the task object to be updated
   * @return TaskDTO
   */
  @PutMapping("/tasks")
  public TaskDTO updateTask(@RequestBody @Valid Task task) {
    return taskServiceImpl.updateTask(task);
  }

  /**
   * @param taskRequest the request object containing the keyword related to the task
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

  /**
   * Retrieves a task by its ID and deletes it.
   *
   * @param taskId the ID of the task to delete
   * @return true if the task was successfully deleted, false otherwise
   */
  @DeleteMapping("/tasks/{id}")
  public boolean deleteTaskById(@PathVariable("id") Long taskId) {

    return taskServiceImpl.deleteTaskById(taskId);
  }

  /**
   * Retrieves a task by its ID.
   *
   * @param taskId the ID of the task to retrieve
   * @return TaskDTO corresponding to the task, or null if the task does not exist
   */
  @GetMapping("/tasks/{id}")
  public TaskDTO getTaskById(@PathVariable("id") Long taskId) {

    return taskServiceImpl.getTaskById(taskId);
  }
}
