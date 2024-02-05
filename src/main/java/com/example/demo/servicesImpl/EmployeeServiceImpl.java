package com.example.demo.servicesImpl;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.mappers.FromDOToDTO;
import com.example.demo.entities.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.services.EmployeeService;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Employee Service Implementation
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private FromDOToDTO fromDOToDTO;

    /**
     * @return List<EmployeeDTO>
     */
    public List<EmployeeDTO> getAllEmployees() {
        final List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeesDto = new ArrayList<>();
        employees.forEach(
                employee -> {
                    EmployeeDTO employeeDTO = fromDOToDTO.MapEmployee(employee);
                    employeesDto.add(employeeDTO);
                });

        return employeesDto;
    }

    /**
     * @param employee
     * @return String
     */
    public String addEmployee(Employee employee) {
        final Employee savedEmployee = employeeRepository.save(employee);
        if (savedEmployee != null && savedEmployee.getEmployee_id() != null) {

            return "Employee added successfully";
        } else {

            return "Employee not added successfully";
        }
    }

    /**
     * @param employee
     * @return String
     */
    public String updateEmployee(Employee employee) {
        final Employee updatedEmployee = employeeRepository.save(employee);
        if (updatedEmployee != null && updatedEmployee.getEmployee_id() != null) {

            return "Employee is updated successfully";
        } else {

            return "Employee not updated successfully";
        }
    }

    /**
     * @param employeeId
     * @return String
     */
    public String deleteEmployee(Long employeeId) {
        final Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());

            return "Employee is deleted successfully";
        } else {

            return "Employee not deleted successfully";
        }
    }

    /**
     * @param keyword
     * @return List<EmployeeDTO>
     */
    public List<EmployeeDTO> searchEmployees(String keyword) {
        final List<Employee> employees = employeeRepository.findByNameContaining(keyword);
        List<EmployeeDTO> employeesDto = new ArrayList<>();
        employees.forEach(
                employee -> {
                    EmployeeDTO employeeDTO = fromDOToDTO.MapEmployee(employee);
                    employeesDto.add(employeeDTO);
                });

        return employeesDto;
    }

    /**
     * @param yearsOfExperience
     * @return List<Employee>
     */
    public List<Employee> getExperiencedEmployees(int yearsOfExperience) {

        return employeeRepository
                .findAll()
                .stream()
                .filter(e -> calculateExperience(e.getJoiningDate()) >= yearsOfExperience)
                .collect(Collectors.toList());
    }

    /**
     * @param joiningDate
     * @return int
     */
    private int calculateExperience(LocalDate joiningDate) {
        return Period.between(joiningDate, LocalDate.now()).getYears();
    }

    /**
     * @param minAge
     * @param maxAge
     * @return List<Employee>
     */
    public List<Employee> filterEmployeesByAge(int minAge, int maxAge) {
        final List<Employee> employees = employeeRepository.findAll();

        return employees
                .stream()
                .filter(
                        employee -> {
                            int age = calculateAge(employee.getDateOfBirth());

                            return age >= minAge && age <= maxAge;
                        })
                .collect(Collectors.toList());
    }

    /**
     * @param dateOfBirth
     * @return int
     */
    private int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

}
