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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
@RestController
public class ProjectController {
  @Autowired private ProjectServiceImpl projectServiceImpl;
  @Autowired private ProjectMapper projectMapper;

  /**
   *
   * @param project
   * @return ProjectDTO
   */
  @PostMapping("/projects")
  public ProjectDTO addProject(@RequestBody @Valid Project project) {
    return projectServiceImpl.addProject(project);
  }

  /**
   *
   * @param project
   * @return ProjectDTO
   */
  @PutMapping("/projects")
  public ProjectDTO updateProject(@RequestBody @Valid Project project) {
    return projectServiceImpl.updateProject(project);
  }

  /**
   *
   * @param projectRequest
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
