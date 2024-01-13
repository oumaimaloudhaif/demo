package com.example.demo.ServicesImpl;

import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Dto.ProjectDTO;
import com.example.demo.Entities.Employee;
import com.example.demo.Entities.Project;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Repository.ProjectRepository;
import com.example.demo.Services.ProjectService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Project Service Implementation
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private FromDOToDTO fromDOToDTO;
    private static final Log LOG = LogFactory.getLog(ProjectServiceImpl.class);
    private static final String PROJECT_NULL = "Project cannot be null";

    /**
     *
     * @return all Projects
     */
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects=projectRepository.findAll();
        List<ProjectDTO> projectDTOS=new ArrayList<>();
        projects.forEach(project -> {
            ProjectDTO projectDTO=fromDOToDTO.MapProject(project);
            projectDTOS.add(projectDTO);
        });
        return projectDTOS;
    }

    /**
     *
     * @param projectId
     * @param employeeIds
     *
     */
    public void assignEmployeesToProject(Long projectId, List<Long> employeeIds) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            List<Employee> employees = employeeRepository.findAllById(employeeIds);
            project.setEmployees(employees);
            projectRepository.save(project);
        }
    }

    /**
     *
     * @param projectId
     * @return
     */
    public Map<Integer, List<Employee>> groupEmployeesByAgeInProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            return project.getEmployees().stream()
                    .collect(Collectors.groupingBy(
                            e -> Period.between(e.getDateOfBirth(), LocalDate.now()).getYears()
                    ));
        }
        else{
            LOG.error(PROJECT_NULL);
        }
        return Collections.emptyMap();
    }

    /**
     *
     * @param projectId
     * @param skills
     * @return
     */
    public Map<String, List<Employee>> filterAndGroupEmployeesBySkillsInProject(Long projectId, List<String> skills) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            return project.getEmployees().stream()
                    .filter(e -> e.getSkills().containsAll(skills))
                    .collect(Collectors.groupingBy(Employee::getName));
        }
        else{
            LOG.error(PROJECT_NULL);
        }
        return Collections.emptyMap();
    }

    /**
     *
     * @param projectId
     * @return
     */
    public boolean hasActiveEmployees(Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            return project.getEmployees().stream()
                    .anyMatch(Employee::isActive);
        }
        else{
            LOG.error(PROJECT_NULL);
        }
        return false;
    }
    public  List<ProjectDTO> searchProject(String keyword) {
        List<Project> projects=projectRepository.findByName(keyword);
        List<ProjectDTO> projectDTOS=new ArrayList<>();
        projects.forEach(project -> {
            ProjectDTO projectDTO=fromDOToDTO.MapProject(project);
            projectDTOS.add(projectDTO);
        });
        return projectDTOS;
    }

    public  Project addProject(Project project) {
        return projectRepository.save(project);
    }

    public  Project updateProject(Project project) {
        return projectRepository.save(project);
    }

}
