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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Department Controller */
@Validated
@RestController
public class DepartmentController {
  @Autowired private DepartmentServiceImpl departmentServiceImpl;
  @Autowired private DepartmentMapper departmentMapper;

  /**
   * Adds a new department
   *
   * @param department the department object to be added
   * @return DepartmentDTO
   */
  @PostMapping("/departments")
  public DepartmentDTO addDepartment(@RequestBody @Valid Department department) {
    return departmentServiceImpl.addDepartment(department);
  }

  /**
   * Updates an existing department
   *
   * @param department the department object to be updated
   * @return DepartmentDTO
   */
  @PutMapping("/departments")
  public DepartmentDTO updateDepartment(@RequestBody @Valid Department department) {
    return departmentServiceImpl.updateDepartment(department);
  }

  /**
   * @param departmentRequest the request object containing the keyword related to the department
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

  /**
   * Retrieves a department by its ID and deletes it.
   *
   * @param departmentId the ID of the department to delete
   * @return true if the department was successfully deleted, false otherwise
   */
  @DeleteMapping("/departments/{id}")
  public boolean deleteDepartmentById(@PathVariable("id") Long departmentId) {

    return departmentServiceImpl.deleteDepartmentById(departmentId);
  }

  /**
   * Retrieves a department by its ID.
   *
   * @param departmentId the ID of the department to retrieve
   * @return DepartmentDTO corresponding to the department, or null if the department does not exist
   */
  @GetMapping("/departments/{id}")
  public DepartmentDTO getDepartmentById(@PathVariable("id") Long departmentId) {

    return departmentServiceImpl.getDepartmentById(departmentId);
  }
}
