package com.example.demo.ServicesImpl;

import com.example.demo.dto.Mappers.FromDOToDTO;
import com.example.demo.dto.TaskDTO;
import com.example.demo.entities.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.services.TaskService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Task Service Impl
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private FromDOToDTO fromDOToDTO;

    /**
     * @param keyword
     * @return List<TaskDTO>
     */
    public List<TaskDTO> searchTasks(String keyword) {
        final List<Task> tasks = taskRepository.findByName(keyword);
        List<TaskDTO> tasksDTo = new ArrayList<>();
        tasks.forEach(
                task -> {
                    TaskDTO taskDTO = fromDOToDTO.MapTask(task);
                    tasksDTo.add(taskDTO);
                });

        return tasksDTo;
    }

    /**
     * @param task
     * @return TaskDTO
     */
    @Override
    public TaskDTO addTask(Task task) {
        final Task savedTask = taskRepository.save(task);

        return fromDOToDTO.MapTask(savedTask);
    }

    /**
     * @param task
     * @return TaskDTO
     */
    @Override
    public TaskDTO updateTask(Task task) {
        final Task updatedTask = taskRepository.save(task);

        return fromDOToDTO.MapTask(updatedTask);
    }

    /**
     * @return List<TaskDTO>
     */
    @Override
    public List<TaskDTO> getAllTasks() {
        final List<Task> tasks = taskRepository.findAll();
        List<TaskDTO> tasksDTo = new ArrayList<>();
        tasks.forEach(
                task -> {
                    TaskDTO taskDTO = fromDOToDTO.MapTask(task);
                    tasksDTo.add(taskDTO);
                });

        return tasksDTo;
    }
}
