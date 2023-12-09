package com.example.demo.ServicesImpl;

import com.example.demo.Entities.Task;
import com.example.demo.Repository.TaskRepository;
import com.example.demo.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    /**
     *
     * @return all tasks
     */

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
