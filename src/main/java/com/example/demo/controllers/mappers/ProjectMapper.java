package com.example.demo.controllers.mappers;

import com.example.demo.controllers.response.ProjectResponse;
import com.example.demo.dto.ProjectDTO;
import java.util.List;
import org.springframework.stereotype.Component;

/*** Project Mapper ***/
@Component
public class ProjectMapper {
  public ProjectResponse toProjectResponse(List<ProjectDTO> projects) {
    ProjectResponse projectResponse = new ProjectResponse();
    projectResponse.setResult(projects);

    return projectResponse;
  }
}
