package com.example.demo.services;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.entities.Project;
import java.util.List;

/** Project Service */
public interface ProjectService {
  List<ProjectDTO> getAllProjects();

  List<ProjectDTO> searchProject(String keyword);

  ProjectDTO addProject(Project project);

  ProjectDTO updateProject(Project project);

  ProjectDTO getProjectById(Long meetingId);

  boolean deleteProjectById(Long meetingId);
}
