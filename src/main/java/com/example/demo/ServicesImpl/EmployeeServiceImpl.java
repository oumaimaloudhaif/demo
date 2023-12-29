package com.example.demo.ServicesImpl;

import com.example.demo.Entities.Employee;
import com.example.demo.Entities.Meeting;
import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Services.EmployeeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Employee Service Implementation
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    private static final Log LOG = LogFactory.getLog(EmployeeServiceImpl.class);
    private static final String EMPLOYEE_NULL = "Employee cannot be null";

    /**
     *
     * @return
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     *
     * @return String
     */
    public String addEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        if (savedEmployee != null && savedEmployee.getEmployee_id() != null) {
            return "Employee added successfully";
        } else {
            return "Employee not added successfully";
        }
    }

    /**
     *
     * @return String
     */
    public String updateEmployee(Employee employee) {
        Employee updatedEmployee = employeeRepository.save(employee);
        if (updatedEmployee != null && updatedEmployee.getEmployee_id() != null) {
            return "Employee is updated successfully";
        } else {
            return "Employee not updated successfully";
        }
    }
    /**
     *
     * @return String
     */
    public String deleteEmployee(Long employeeId) {
        Optional<Employee> employee=employeeRepository.findById(employeeId);
        if(employee.isPresent()){ employeeRepository.delete(employee.get());
              return "Employee is deleted successfully";}
       else{
             return "Employee not deleted successfully";}

    }
    /**
     *
     * @param keyword
     * @return
     */
    public List<Employee> searchEmployees(String keyword) {
        return employeeRepository.findByNameContaining(keyword);
    }

    /**
     *
     * @param yearsOfExperience
     * @return
     */
    public List<Employee> getExperiencedEmployees(int yearsOfExperience) {
        return employeeRepository.findAll().stream()
                .filter(e -> calculateExperience(e.getJoiningDate()) >= yearsOfExperience)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param joiningDate
     * @return
     */
    private int calculateExperience(LocalDate joiningDate) {
        return Period.between(joiningDate, LocalDate.now()).getYears();
    }

    /**
     *
     * @param minAge
     * @param maxAge
     * @return
     */
    public List<Employee> filterEmployeesByAge(int minAge, int maxAge) {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .filter(employee -> {
                    int age = calculateAge(employee.getDateOfBirth());
                    return age >= minAge && age <= maxAge;
                })
                .collect(Collectors.toList());
    }

    /**
     *
     * @param dateOfBirth
     * @return
     */
    private int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    /**
     *
     * @param gender
     * @return
     */
    public List<Employee> filterEmployeesByGender(Gender gender) {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .filter(employee -> employee.getGender() == gender)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param employeeId
     * @return
     */
    public int calculateEmployeeSeniority(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee != null) {
            LocalDate currentDate = LocalDate.now();
            return Period.between(employee.getJoiningDate(), currentDate).getYears();
        }
        else{LOG.error(EMPLOYEE_NULL);}
        return 0;
    }

    /**
     *
     * @param minSalary
     * @param maxSalary
     * @return
     */
    public List<Employee> getEmployeesInSalaryRange(double minSalary, double maxSalary) {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .filter(employee -> employee.getSalary() >= minSalary && employee.getSalary() <= maxSalary)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param employeeId
     * @param skill
     * @return
     */
    public boolean hasEmployeeSkill(Long employeeId, String skill) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        return employee != null && employee.getSkills().contains(skill);
    }

    /**
     *
     * @param employeeId
     * @return
     */
    public boolean isEmployeeInCDI(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        return employee != null && employee.getContractType() == ContractType.CDI;
    }

    /**
     *
     * @return
     */
    public List<Employee> getEmployeesBySeniority() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .sorted(Comparator.comparingInt(employee -> Period.between(employee.getJoiningDate(), LocalDate.now()).getYears()))
                .collect(Collectors.toList());
    }

}
