package com.example.demo.Controllers;

import com.example.demo.Controllers.Mappers.EmployeeMapper;
import com.example.demo.Controllers.Request.EmployeeRequest;
import com.example.demo.Controllers.Response.FetchEmployeeResponse;
import com.example.demo.Entities.Employee;
import com.example.demo.ServicesImpl.EmployeeServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class EmployeeController {
  @Autowired private EmployeeServiceImpl employeeServiceImpl;
  @Autowired private EmployeeMapper employeeMapper;

  @PostMapping("/employees")
  public String addEmployee(@RequestBody Employee employee) {
    return employeeServiceImpl.addEmployee(employee);
  }

  @PutMapping("/employees")
  public String updateEmployee(@RequestBody Employee employee) {
    return employeeServiceImpl.updateEmployee(employee);
  }

  @DeleteMapping("/employees/{employeeId}")
  public String DeleteEmployee(@PathVariable Long employeeId) {
    return employeeServiceImpl.deleteEmployee(employeeId);
  }

  /**
   * @param keyword
   * @return
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
      return employeeMapper.toFetchEmployeeResponse(employeeServiceImpl.getAllEmployees());
    }
  }
}
