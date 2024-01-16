package com.example.demo.Services;

import com.example.demo.Dto.ProjectDTO;
import com.example.demo.Entities.Employee;
import com.example.demo.Entities.Project;
import java.util.List;
import java.util.Map;

/** Project Service */
public interface ProjectService {
  List<ProjectDTO> getAllProjects();

  void assignEmployeesToProject(Long projectId, List<Long> employeeIds);

  Map<Integer, List<Employee>> groupEmployeesByAgeInProject(Long projectId);

  Map<String, List<Employee>> filterAndGroupEmployeesBySkillsInProject(
      Long projectId, List<String> skills);

  boolean hasActiveEmployees(Long projectId);

  List<ProjectDTO> searchProject(String keyword);

  ProjectDTO addProject(Project project);

  ProjectDTO updateProject(Project project);
}
