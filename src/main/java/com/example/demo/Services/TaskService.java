package com.example.demo.Services;

import com.example.demo.Dto.TaskDTO;
import com.example.demo.Entities.Task;

import java.util.List;

/**
 * Task Service
 */
public interface TaskService {
    List<TaskDTO> searchTasks(String keyword);

    Task addTask(Task task);

    Task updateTask(Task task);


    List<TaskDTO> getAllTasks();
}
