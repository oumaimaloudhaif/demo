package com.example.demo.servicesImpl;

import com.example.demo.dto.mappers.FromDOToDTO;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.entities.Project;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.services.ProjectService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project Service Implementation
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private FromDOToDTO fromDOToDTO;

    /**
     * @return List<ProjectDTO>
     */
    public List<ProjectDTO> getAllProjects() {
        final List<Project> projects = projectRepository.findAll();
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        projects.forEach(
                project -> {
                    ProjectDTO projectDTO = fromDOToDTO.MapProject(project);
                    projectDTOS.add(projectDTO);
                });

        return projectDTOS;
    }

    /**
     * @param keyword
     * @return List<ProjectDTO>
     */
    public List<ProjectDTO> searchProject(String keyword) {
        final List<Project> projects = projectRepository.findByName(keyword);
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        projects.forEach(
                project -> {
                    ProjectDTO projectDTO = fromDOToDTO.MapProject(project);
                    projectDTOS.add(projectDTO);
                });
        return projectDTOS;
    }

    /**
     * @param project
     * @return ProjectDTO
     */
    public ProjectDTO addProject(Project project) {
        final Project savedProject = projectRepository.save(project);
        return fromDOToDTO.MapProject(savedProject);
    }

    /**
     * @param project
     * @return ProjectDTO
     */
    public ProjectDTO updateProject(Project project) {
        final Project updatedProject = projectRepository.save(project);
        return fromDOToDTO.MapProject(updatedProject);
    }
}
