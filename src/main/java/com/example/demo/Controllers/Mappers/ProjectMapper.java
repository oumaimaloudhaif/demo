package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.ProjectResponse;
import com.example.demo.Dto.ProjectDTO;
import com.example.demo.Entities.Project;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectMapper {
    public ProjectResponse toProjectResponse(List<ProjectDTO> projects){
        ProjectResponse projectResponse=new ProjectResponse();
        projectResponse.setResult(projects);
        return projectResponse;
    }
}