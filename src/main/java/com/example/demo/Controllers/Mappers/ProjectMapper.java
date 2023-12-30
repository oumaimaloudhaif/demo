package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.ProjectResponse;
import com.example.demo.Entities.Project;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectMapper {
    public ProjectResponse toProjectResponse(List<Project> projects){
        ProjectResponse projectResponse=new ProjectResponse();
        projectResponse.setResult(projects);
        return projectResponse;
    }
}