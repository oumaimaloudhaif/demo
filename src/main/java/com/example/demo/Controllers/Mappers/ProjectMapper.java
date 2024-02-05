package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.ProjectResponse;
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
