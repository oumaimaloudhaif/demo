package com.example.demo.Services;

import com.example.demo.Dto.EmployeeDTO;
import com.example.demo.Entities.Employee;
import com.example.demo.Enums.Gender;
import java.util.List;

/**
 * Employee Service
 */
public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    List<EmployeeDTO> searchEmployees(String keyword);
    List<Employee> getExperiencedEmployees(int yearsOfExperience) ;
    public List<Employee> filterEmployeesByAge(int minAge, int maxAge);
    List<Employee> filterEmployeesByGender(Gender gender);
    int calculateEmployeeSeniority(Long employeeId) ;
    List<Employee> getEmployeesInSalaryRange(double minSalary, double maxSalary) ;
    boolean hasEmployeeSkill(Long employeeId, String skill) ;
   List<Employee> getEmployeesBySeniority() ;


}
