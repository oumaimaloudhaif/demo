package com.example.demo.Services;

import com.example.demo.Dto.TaskDTO;
import com.example.demo.Entities.Task;
import java.util.List;

/** Task Service */
public interface TaskService {
  List<TaskDTO> searchTasks(String keyword);

  TaskDTO addTask(Task task);

  TaskDTO updateTask(Task task);

  List<TaskDTO> getAllTasks();
}
