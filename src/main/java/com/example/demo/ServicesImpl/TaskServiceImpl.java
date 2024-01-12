package com.example.demo.ServicesImpl;

import com.example.demo.Entities.Task;
import com.example.demo.Repository.TaskRepository;
import com.example.demo.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Task Service Impl
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public  List<Task> searchTasks(String keyword) {
        return taskRepository.findByName(keyword);
    }
    @Override
    public  Task addTask(Task task) {
       return taskRepository.save(task);
    }
    @Override
    public  Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
