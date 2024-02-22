package com.example.demo.servicesImpl;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.dto.mappers.FromDOToDTO;
import com.example.demo.entities.Project;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.services.ProjectService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Project Service Implementation */
@Service
public class ProjectServiceImpl implements ProjectService {
  @Autowired private ProjectRepository projectRepository;
  @Autowired private FromDOToDTO fromDOToDTO;

  /** @return List<ProjectDTO> */
  @Override
  public List<ProjectDTO> getAllProjects() {
    final List<Project> projects = projectRepository.findAll();
    List<ProjectDTO> projectDTOS = new ArrayList<>();
    projects.forEach(
        project -> {
          ProjectDTO projectDTO = fromDOToDTO.mapProject(project);
          projectDTOS.add(projectDTO);
        });

    return projectDTOS;
  }

  /**
   * @param keyword a keyword (project name) to search for projects
   * @return List<ProjectDTO>
   */
  @Override
  public List<ProjectDTO> searchProject(String keyword) {
    final List<Project> projects = projectRepository.findByName(keyword);
    List<ProjectDTO> projectDTOS = new ArrayList<>();
    projects.forEach(
        project -> {
          ProjectDTO projectDTO = fromDOToDTO.mapProject(project);
          projectDTOS.add(projectDTO);
        });
    return projectDTOS;
  }

  /**
   * @param project the project object to be added
   * @return ProjectDTO
   */
  @Override
  public ProjectDTO addProject(Project project) {
    final Project savedProject = projectRepository.save(project);
    return fromDOToDTO.mapProject(savedProject);
  }

  /**
   * @param project the project object to be updated
   * @return ProjectDTO
   */
  @Override
  public ProjectDTO updateProject(Project project) {
    final Project updatedProject = projectRepository.save(project);
    return fromDOToDTO.mapProject(updatedProject);
  }

  /**
   * Retrieves a project by its ID.
   *
   * @param projectId the ID of the project to retrieve
   * @return the ProjectDTO corresponding to the project, or null if the project does not exist
   */
  @Override
  public ProjectDTO getProjectById(Long projectId) {
    final Project project = projectRepository.findById(projectId).orElse(null);
    if (project != null) {

      return fromDOToDTO.mapProject(project);
    } else {

      return null;
    }
  }

  /**
   * Deletes a project by its ID.
   *
   * @param projectId the ID of the project to delete
   * @return true if the project was deleted successfully, false otherwise
   */
  @Override
  public boolean deleteProjectById(Long projectId) {
    final Project project = projectRepository.findById(projectId).orElse(null);
    if (project != null) {
      projectRepository.delete(project);

      return true;
    } else {

      return false;
    }
  }
}
