package com.example.demo.servicesImpl;

import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.mappers.FromDOToDTO;
import com.example.demo.entities.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.services.TaskService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Task Service Impl */
@Service
public class TaskServiceImpl implements TaskService {
  @Autowired private TaskRepository taskRepository;
  @Autowired private FromDOToDTO fromDOToDTO;

  /**
   * @param keyword a keyword (task name) to search for tasks
   * @return List<TaskDTO>
   */
  public List<TaskDTO> searchTasks(String keyword) {
    final List<Task> tasks = taskRepository.findByName(keyword);
    List<TaskDTO> tasksDTo = new ArrayList<>();
    tasks.forEach(
        task -> {
          TaskDTO taskDTO = fromDOToDTO.mapTask(task);
          tasksDTo.add(taskDTO);
        });

    return tasksDTo;
  }

  /**
   * @param task the task object to be added
   * @return TaskDTO
   */
  @Override
  public TaskDTO addTask(Task task) {
    final Task savedTask = taskRepository.save(task);

    return fromDOToDTO.mapTask(savedTask);
  }

  /**
   * @param task the task object to be updated
   * @return TaskDTO
   */
  @Override
  public TaskDTO updateTask(Task task) {
    final Task updatedTask = taskRepository.save(task);

    return fromDOToDTO.mapTask(updatedTask);
  }

  /** @return List<TaskDTO> */
  @Override
  public List<TaskDTO> getAllTasks() {
    final List<Task> tasks = taskRepository.findAll();
    List<TaskDTO> tasksDTo = new ArrayList<>();
    tasks.forEach(
        task -> {
          TaskDTO taskDTO = fromDOToDTO.mapTask(task);
          tasksDTo.add(taskDTO);
        });

    return tasksDTo;
  }

  /**
   * Retrieves a task by its ID.
   *
   * @param taskId the ID of the task to retrieve
   * @return the TaskDTO corresponding to the task, or null if the task does not exist
   */
  @Override
  public TaskDTO getTaskById(Long taskId) {
    final Task task = taskRepository.findById(taskId).orElse(null);
    if (task != null) {

      return fromDOToDTO.mapTask(task);
    } else {

      return null;
    }
  }

  /**
   * Deletes a task by its ID.
   *
   * @param taskId the ID of the task to delete
   * @return true if the task was deleted successfully, false otherwise
   */
  @Override
  public boolean deleteTaskById(Long taskId) {
    final Task task = taskRepository.findById(taskId).orElse(null);
    if (task != null) {
      taskRepository.delete(task);

      return true;
    } else {

      return false;
    }
  }
}
