package com.example.demo.controllers;

import com.example.demo.controllers.mappers.EmployeeMapper;
import com.example.demo.controllers.request.EmployeeRequest;
import com.example.demo.controllers.response.FetchEmployeeResponse;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.servicesImpl.EmployeeServiceImpl;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Employee Controller */
@Validated
@RestController
public class EmployeeController {
  @Autowired private EmployeeServiceImpl employeeServiceImpl;
  @Autowired private EmployeeMapper employeeMapper;

  /**
   * Adds a new employee
   *
   * @param employee the employee object to be added
   * @return String
   */
  @PostMapping("/employees")
  public String addEmployee(@RequestBody Employee employee) {
    return employeeServiceImpl.addEmployee(employee);
  }

  /**
   * Updates an existing employee
   *
   * @param employee the employee object to be updated
   * @return String
   */
  @PutMapping("/employees")
  public String updateEmployee(@RequestBody Employee employee) {
    return employeeServiceImpl.updateEmployee(employee);
  }

  /**
   * Delete an employee
   *
   * @param employeeId the ID of the employee to be deleted
   * @return String
   */
  @DeleteMapping("/employees/{employeeId}")
  public String DeleteEmployee(@PathVariable Long employeeId) {
    return employeeServiceImpl.deleteEmployee(employeeId);
  }

  /**
   * @param keyword a keyword to search for employees
   * @param employeeRequest the request object containing the keyword related to the employee
   * @return FetchEmployeeResponse
   */
  @GetMapping("/employees")
  public FetchEmployeeResponse getEmployees(
      @RequestParam(required = false) @Valid String keyword,
      @RequestBody(required = false) @Valid EmployeeRequest employeeRequest) {
    if (keyword != null) {
      return employeeMapper.toFetchEmployeeResponse(employeeServiceImpl.searchEmployees(keyword));
    } else if (employeeRequest != null && employeeRequest.getKeyword() != null) {
      return employeeMapper.toFetchEmployeeResponse(
          employeeServiceImpl.searchEmployees(employeeRequest.getKeyword()));
    } else {
      List<EmployeeDTO> employees;
      employees = employeeServiceImpl.getAllEmployees();
      return employeeMapper.toFetchEmployeeResponse(employees);
    }
  }
}
