package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.TaskResponse;
import com.example.demo.Entities.Task;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TaskMapper {
    public TaskResponse toTasksResponse(List<Task> tasks){
        TaskResponse tasksResponse=new TaskResponse();
        tasksResponse.setResult(tasks);
        return tasksResponse;
    }
}
