package com.example.demo.controllers;

import com.example.demo.controllers.mappers.ProjectMapper;
import com.example.demo.controllers.request.ProjectRequest;
import com.example.demo.controllers.response.ProjectResponse;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.entities.Project;
import com.example.demo.servicesImpl.ProjectServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Project Controller */
@Validated
@RestController
public class ProjectController {
  @Autowired private ProjectServiceImpl projectServiceImpl;
  @Autowired private ProjectMapper projectMapper;

  /**
   * Adds a new project
   *
   * @param project the project object to be added
   * @return ProjectDTO
   */
  @PostMapping("/projects")
  public ProjectDTO addProject(@RequestBody @Valid Project project) {
    return projectServiceImpl.addProject(project);
  }

  /**
   * Updates an existing project
   *
   * @param project the project object to be updated
   * @return ProjectDTO
   */
  @PutMapping("/projects")
  public ProjectDTO updateProject(@RequestBody @Valid Project project) {
    return projectServiceImpl.updateProject(project);
  }

  /**
   * @param projectRequest the request object containing the keyword related to the project
   * @return ProjectResponse
   */
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
