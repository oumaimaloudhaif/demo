package com.example.demo.servicesImpl;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.mappers.FromDOToDTO;
import com.example.demo.entities.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.services.EmployeeService;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Employee Service Implementation */
@Service
public class EmployeeServiceImpl implements EmployeeService {
  @Autowired private EmployeeRepository employeeRepository;
  @Autowired private FromDOToDTO fromDOToDTO;

  /** @return List<EmployeeDTO> */
  public List<EmployeeDTO> getAllEmployees() {
    final List<Employee> employees = employeeRepository.findAll();
    List<EmployeeDTO> employeesDto = new ArrayList<>();
    employees.forEach(
        employee -> {
          EmployeeDTO employeeDTO = fromDOToDTO.mapEmployee(employee);
          employeesDto.add(employeeDTO);
        });

    return employeesDto;
  }

  /**
   * @param employee the employee object to be added
   * @return String
   */
  @Override
  public String addEmployee(Employee employee) {
    final Employee savedEmployee = employeeRepository.save(employee);
    if (savedEmployee.getEmployee_id() != null) {

      return "Employee added successfully";
    } else {

      return "Employee not added successfully";
    }
  }

  /**
   * @param employee the employee object to be updated
   * @return String
   */
  @Override
  public String updateEmployee(Employee employee) {
    final Employee updatedEmployee = employeeRepository.save(employee);
    if (updatedEmployee.getEmployee_id() != null) {

      return "Employee is updated successfully";
    } else {

      return "Employee not updated successfully";
    }
  }

  /**
   * @param employeeId the ID of the employee to be deleted
   * @return String
   */
  public String deleteEmployee(Long employeeId) {
    Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
    if (employeeOptional.isPresent()) {
      Employee employee = employeeOptional.get();

      employee.getProjects().forEach(project -> project.getEmployees().remove(employee));

      employee.getWorkCalendars().forEach(workCalendar -> workCalendar.setEmployee(null));

      employeeRepository.delete(employee);

      return "Employee is deleted successfully";
    } else {
      return "Employee not found";
    }
  }

  /**
   * Search employees by keyword
   *
   * @param keyword a keyword (employee name) to search for employees
   * @return List<EmployeeDTO>
   */
  @Override
  public List<EmployeeDTO> searchEmployees(String keyword) {
    final List<Employee> employees = employeeRepository.findByNameContaining(keyword);
    List<EmployeeDTO> employeesDto = new ArrayList<>();
    employees.forEach(
        employee -> {
          EmployeeDTO employeeDTO = fromDOToDTO.mapEmployee(employee);
          employeesDto.add(employeeDTO);
        });

    return employeesDto;
  }

  /**
   * Retrieves a list of employees with a minimum specified years of experience.
   *
   * @param yearsOfExperience The minimum years of experience required for an employee to be
   *     considered experienced.
   * @return List<Employee> A list of employees with experience equal to or greater than the
   *     specified years.
   */
  @Override
  public List<Employee> getExperiencedEmployees(int yearsOfExperience) {

    return employeeRepository
        .findAll()
        .stream()
        .filter(e -> calculateExperience(e.getJoiningDate()) >= yearsOfExperience)
        .collect(Collectors.toList());
  }

  /**
   * Calculates the years of experience based on the joining date of an employee.
   *
   * @param joiningDate The joining date of the employee.
   * @return int The number of years of experience since the joining date.
   */
  @Override
  public int calculateExperience(LocalDate joiningDate) {

    return Period.between(joiningDate, LocalDate.now()).getYears();
  }

  /**
   * Filters employees based on the age range specified.
   *
   * @param minAge The minimum age
   * @param maxAge The maximum age
   * @return List<Employee> A list of employees within the specified age range.
   */
  @Override
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
   * Calculates the age based on the given date of birth.
   *
   * @param dateOfBirth The date of birth of the employee.
   * @return int The age calculated from the date of birth.
   */
  @Override
  public int calculateAge(LocalDate dateOfBirth) {

    return Period.between(dateOfBirth, LocalDate.now()).getYears();
  }

  /**
   * Retrieves a department by its ID.
   *
   * @param employee_Id the ID of the employee to retrieve
   * @return the EmployeeDTO corresponding to the employee, or null if the employee does not exist
   */
  @Override
  public EmployeeDTO getEmployeeById(Long employee_Id) {
    final Employee employee = employeeRepository.findById(employee_Id).orElse(null);
    if (employee != null) {

      return fromDOToDTO.mapEmployee(employee);
    } else {

      return null;
    }
  }
}
