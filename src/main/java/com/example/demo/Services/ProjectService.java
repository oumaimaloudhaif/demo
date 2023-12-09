package com.example.demo.Services;

import com.example.demo.Entities.Employee;
import com.example.demo.Entities.Project;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ProjectService {
    List<Project> getAllProjects();
    void assignEmployeesToProject(Long projectId, List<Long> employeeIds) ;
    Map<Integer, List<Employee>> groupEmployeesByAgeInProject(Long projectId);
    Map<String, List<Employee>> filterAndGroupEmployeesBySkillsInProject(Long projectId, List<String> skills) ;
    int calculateTotalProjectHours(Long projectId) ;
    boolean hasActiveEmployees(Long projectId) ;
}
