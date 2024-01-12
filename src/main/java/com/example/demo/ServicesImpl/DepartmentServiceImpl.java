package com.example.demo.ServicesImpl;

import com.example.demo.Entities.Department;
import com.example.demo.Entities.Employee;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.Services.DepartmentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Department Service Implementation
 *
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    private static final Log LOG = LogFactory.getLog(DepartmentServiceImpl.class);
    private static final String DEPARTMENT_NULL = "Department cannot be null";

    /**
     *
     * @return
     */
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    /**
     *
     * @return
     */

    public Map<Department, Long> getEmployeeCountPerDepartment() {
        List<Department> departments = departmentRepository.findAll();
        Map<Department, Long> employeeCountMap = new HashMap<>();
        for (Department department : departments) {
            long employeeCount = department.getEmployees().size();
            employeeCountMap.put(department, employeeCount);
        }
        return employeeCountMap;
    }

    /**
     *
     * @param departmentId
     * @return
     */
    public Set<String> mergeSkillsInDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department != null) {
            return department.getEmployees().stream()
                    .flatMap(e -> e.getSkills().stream())
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    /**
     *
     * @return
     */
    public Map<Department, Optional<Employee>> getHighestPaidEmployeesInEachDepartment() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .collect(Collectors.toMap(
                        department -> department,
                        department -> department.getEmployees().stream()
                                .max(Comparator.comparing(Employee::getSalary))
                ));
    }

    /**
     *
     * @param departmentId
     * @return
     */
    public double calculateAverageSalaryInDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department != null) {
            return department.getEmployees().stream()
                    .mapToDouble(Employee::getSalary)
                    .average()
                    .orElse(0.0);
        }
        else{
            LOG.error(DEPARTMENT_NULL);
        }
        return 0.0;
    }

    /**
     *
     * @return
     */
    public Map<Department, Employee> getYoungestEmployeesInEachDepartment() {
        List<Department> departments = departmentRepository.findAll();
        Map<Department, Employee> youngestEmployees = new HashMap<>();
        for (Department department : departments) {
            Optional<Employee> youngestEmployee = department.getEmployees().stream()
                    .min(Comparator.comparing(Employee::getDateOfBirth));

            youngestEmployee.ifPresent(employee -> youngestEmployees.put(department, employee));
        }

        return youngestEmployees;
    }

    /**
     *
     * @param departmentId
     * @return
     */
    public Map<Float, List<Employee>> groupEmployeesBySalaryInDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department != null) {
            return department.getEmployees().stream()
                    .collect(Collectors.groupingBy(Employee::getSalary));
        }
        else{
            LOG.error(DEPARTMENT_NULL);
        }
        return Collections.emptyMap();
    }
    public  List<Department> searchDepartment(String keyword) {
        return departmentRepository.findByName(keyword);
    }

    public  Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public  Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

}
