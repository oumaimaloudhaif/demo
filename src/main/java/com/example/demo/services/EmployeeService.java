package com.example.demo.services;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entities.Employee;
import java.time.LocalDate;
import java.util.List;

/** Employee Service */
public interface EmployeeService {
  List<EmployeeDTO> getAllEmployees();

  List<EmployeeDTO> searchEmployees(String keyword);

  List<Employee> getExperiencedEmployees(int yearsOfExperience);

  List<Employee> filterEmployeesByAge(int minAge, int maxAge);

  EmployeeDTO getEmployeeById(Long employee_Id);

  int calculateAge(LocalDate dateOfBirth);

  int calculateExperience(LocalDate joiningDate);

  String updateEmployee(Employee employee);

  String addEmployee(Employee employee);

  String deleteEmployee(Long employeeId);
}
