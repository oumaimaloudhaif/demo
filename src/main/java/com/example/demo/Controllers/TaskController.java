package com.example.demo.Controllers;

import com.example.demo.Entities.Task;
import com.example.demo.ServicesImpl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private TaskServiceImpl taskServiceImpl;

    /**
     *
     * @return
     */
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskServiceImpl.getAllTasks();
    }

}
