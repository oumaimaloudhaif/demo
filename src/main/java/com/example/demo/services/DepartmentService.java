package com.example.demo.services;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entities.Department;
import java.util.List;

/** Department Service */
public interface DepartmentService {

  List<DepartmentDTO> getAllDepartments();

  List<DepartmentDTO> searchDepartment(String keyword);

  DepartmentDTO addDepartment(Department department);

  DepartmentDTO updateDepartment(Department department);

  boolean deleteDepartmentById(Long departmentId);

  DepartmentDTO getDepartmentById(Long departmentId);
}
