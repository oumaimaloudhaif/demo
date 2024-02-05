package com.example.demo.controllers;

import com.example.demo.controllers.mappers.DepartmentMapper;
import com.example.demo.controllers.request.DepartmentRequest;
import com.example.demo.controllers.response.DepartmentResponse;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entities.Department;
import com.example.demo.servicesImpl.DepartmentServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class DepartmentController {
  @Autowired private DepartmentServiceImpl departmentServiceImpl;
  @Autowired private DepartmentMapper departmentMapper;

  /**
   *
   * @param department
   * @return DepartmentDTO
   */
  @PostMapping("/departments")
  public DepartmentDTO addDepartment(@RequestBody @Valid Department department) {
    return departmentServiceImpl.addDepartment(department);
  }

  /**
   *
   * @param department
   * @return DepartmentDTO
   */
  @PutMapping("/departments")
  public DepartmentDTO updateDepartment(@RequestBody @Valid Department department) {
    return departmentServiceImpl.updateDepartment(department);
  }

  /**
   *
   * @param departmentRequest
   * @return DepartmentResponse
   */
  @GetMapping("/departments")
  public DepartmentResponse getDepartments(
      @RequestParam(required = false) @Valid DepartmentRequest departmentRequest) {
    if (departmentRequest != null && departmentRequest.getKeyword() != null) {
      return departmentMapper.toDepartmentResponse(
          departmentServiceImpl.searchDepartment(departmentRequest.getKeyword()));
    } else {
      return departmentMapper.toDepartmentResponse(departmentServiceImpl.getAllDepartments());
    }
  }
}
