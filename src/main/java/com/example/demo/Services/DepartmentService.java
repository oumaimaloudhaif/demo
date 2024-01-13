package com.example.demo.Services;

import com.example.demo.Dto.DepartmentDTO;
import com.example.demo.Entities.Department;

import java.util.List;

/**
 * Department Service
 */
public interface DepartmentService {


   List<DepartmentDTO> getAllDepartments() ;
   List<DepartmentDTO> searchDepartment(String keyword) ;

   Department addDepartment(Department department);

   Department updateDepartment(Department department) ;


}