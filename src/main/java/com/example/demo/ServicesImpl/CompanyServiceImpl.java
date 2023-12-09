package com.example.demo.ServicesImpl;

import com.example.demo.Entities.Department;
import com.example.demo.Entities.Employee;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    public boolean isSkillDiverse(String skill) {
        List<Employee> employees = employeeRepository.findAll();
        long countEmployeesWithSkill = employees.stream()
                .filter(employee -> employee.getSkills().contains(skill))
                .count();

        return countEmployeesWithSkill > 1;
    }
    public Department getMostOccupiedDepartment() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .max(Comparator.comparingInt(department -> department.getEmployees().size()))
                .orElse(null);
    }
}
