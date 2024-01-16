package com.example.demo.Controllers;

import com.example.demo.Controllers.Mappers.TaskMapper;
import com.example.demo.Controllers.Request.TaskRequest;
import com.example.demo.Controllers.Response.TaskResponse;
import com.example.demo.Dto.TaskDTO;
import com.example.demo.Entities.Task;
import com.example.demo.ServicesImpl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
public class TaskController {
    @Autowired
    private TaskServiceImpl taskServiceImpl;
    @Autowired
    private TaskMapper tasksMapper;
    /**
     *
     * @return TasksResponse
     */
  /*  @GetMapping("/tasks")
    public TaskResponse getAllTasks() {
        return  tasksMapper.toTasksResponse(taskServiceImpl.getAllTasks());
    }
   */ /**
     *
     *
     *@return Task
     */
    @PostMapping("/tasks")
    public TaskDTO addTask(@RequestBody @Valid Task task) {
        return taskServiceImpl.addTask(task);
    }
    /**
     *
     *
     *@return Task
     */
    @PutMapping("/tasks")
    public TaskDTO updateTask(@RequestBody @Valid Task task) {
        return taskServiceImpl.updateTask(task);
    }
    /**
     *
     * @return TasksResponse
     */
  /*  @GetMapping("/tasks")
    public TaskResponse searchTasks(@RequestParam(required = false) @Valid TaskRequest taskRequest) {
        return tasksMapper.toTasksResponse(taskServiceImpl.searchTasks(taskRequest.getKeyword()));
    }
*/
    @GetMapping("/tasks")
    public TaskResponse getReports(@RequestParam(required = false) @Valid TaskRequest taskRequest) {
        if (taskRequest != null && taskRequest.getKeyword() != null) {
            return tasksMapper.toTasksResponse(taskServiceImpl.searchTasks(taskRequest.getKeyword()));
        } else {
            return tasksMapper.toTasksResponse(taskServiceImpl.getAllTasks());
        }
    }
}
