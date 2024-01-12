package com.example.demo.Services;

import com.example.demo.Entities.Task;

import java.util.List;

/**
 * Task Service
 */
public interface TaskService {
    List<Task> searchTasks(String keyword);

    Task addTask(Task task);

    Task updateTask(Task task);


    List<Task> getAllTasks();
}
