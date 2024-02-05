package com.example.demo.Controllers;

import com.example.demo.Controllers.Mappers.EmployeeMapper;
import com.example.demo.Controllers.Request.EmployeeRequest;
import com.example.demo.Controllers.Response.FetchEmployeeResponse;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.ServicesImpl.EmployeeServiceImpl;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Validated
@RestController
public class EmployeeController {
  @Autowired private EmployeeServiceImpl employeeServiceImpl;
  @Autowired private EmployeeMapper employeeMapper;

  /**
   *
   * @param employee
   * @return String
   */
  @PostMapping("/employees")
  public String addEmployee(@RequestBody Employee employee) {
    return employeeServiceImpl.addEmployee(employee);
  }

  /**
   *
   * @param employee
   * @return String
   */
  @PutMapping("/employees")
  public String updateEmployee(@RequestBody Employee employee) {
    return employeeServiceImpl.updateEmployee(employee);
  }

  /**
   *
   * @param employeeId
   * @return String
   */
  @DeleteMapping("/employees/{employeeId}")
  public String DeleteEmployee(@PathVariable Long employeeId) {
    return employeeServiceImpl.deleteEmployee(employeeId);
  }

  /**
   *
   * @param keyword
   * @param employeeRequest
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
