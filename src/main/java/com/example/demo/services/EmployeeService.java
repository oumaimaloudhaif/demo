package com.example.demo.services;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entities.Employee;
import java.util.List;

/** Employee Service */
public interface EmployeeService {
  List<EmployeeDTO> getAllEmployees();

  List<EmployeeDTO> searchEmployees(String keyword);

  List<Employee> getExperiencedEmployees(int yearsOfExperience);

  List<Employee> filterEmployeesByAge(int minAge, int maxAge);
}
