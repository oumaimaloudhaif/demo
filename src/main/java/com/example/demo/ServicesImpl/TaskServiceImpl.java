package com.example.demo.ServicesImpl;

import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Dto.TaskDTO;
import com.example.demo.Entities.Task;
import com.example.demo.Repository.TaskRepository;
import com.example.demo.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Task Service Impl
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private FromDOToDTO fromDOToDTO;
    public  List<TaskDTO> searchTasks(String keyword) {
        List<Task> tasks=taskRepository.findByName(keyword);
        List<TaskDTO> tasksDTo=new ArrayList<>();
        tasks.forEach(task -> {
            TaskDTO taskDTO =fromDOToDTO.MapTask(task);
            tasksDTo.add(taskDTO);
        });
       return tasksDTo;

    }
    @Override
    public  TaskDTO addTask(Task task) {
       Task savedTask=taskRepository.save(task);
       return fromDOToDTO.MapTask(savedTask);
    }
    @Override
    public  TaskDTO updateTask(Task task) {
        Task updatedTask=taskRepository.save(task);
        return fromDOToDTO.MapTask(updatedTask);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks=taskRepository.findAll();
        List<TaskDTO> tasksDTo=new ArrayList<>();
        tasks.forEach(task -> {
            TaskDTO taskDTO =fromDOToDTO.MapTask(task);
            tasksDTo.add(taskDTO);
        });
        return tasksDTo;
    }
}
