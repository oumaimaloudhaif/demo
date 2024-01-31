package com.example.demo.Controllers;

import com.example.demo.Controllers.Mappers.ProjectMapper;
import com.example.demo.Controllers.Request.ProjectRequest;
import com.example.demo.Controllers.Response.ProjectResponse;
import com.example.demo.Dto.ProjectDTO;
import com.example.demo.Entities.Project;
import com.example.demo.ServicesImpl.ProjectServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class ProjectController {
  @Autowired private ProjectServiceImpl projectServiceImpl;
  @Autowired private ProjectMapper projectMapper;
  /** @return Project */
  @PostMapping("/projects")
  public ProjectDTO addProject(@RequestBody @Valid Project project) {
    return projectServiceImpl.addProject(project);
  }

  /** @return Project */
  @PutMapping("/projects")
  public ProjectDTO updateProject(@RequestBody @Valid Project project) {
    return projectServiceImpl.updateProject(project);
  }

  @GetMapping("/projects")
  public ProjectResponse getMeetings(
      @RequestParam(required = false) @Valid ProjectRequest projectRequest) {
    if (projectRequest != null && projectRequest.getKeyword() != null) {
      return projectMapper.toProjectResponse(
          projectServiceImpl.searchProject(projectRequest.getKeyword()));
    } else {
      return projectMapper.toProjectResponse(projectServiceImpl.getAllProjects());
    }
  }
}
