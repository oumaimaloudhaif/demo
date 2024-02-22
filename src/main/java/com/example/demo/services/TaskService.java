package com.example.demo.services;

import com.example.demo.dto.TaskDTO;
import com.example.demo.entities.Task;
import java.util.List;

/** Task Service */
public interface TaskService {
  List<TaskDTO> searchTasks(String keyword);

  TaskDTO addTask(Task task);

  TaskDTO updateTask(Task task);

  List<TaskDTO> getAllTasks();

  TaskDTO getTaskById(Long taskId);

  boolean deleteTaskById(Long taskId);
}
