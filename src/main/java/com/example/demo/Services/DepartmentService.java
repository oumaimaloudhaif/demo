package com.example.demo.Services;

import com.example.demo.Entities.Department;

import java.util.List;

/**
 * Department Service
 */
public interface DepartmentService {


   List<Department> getAllDepartments() ;
   List<Department> searchDepartment(String keyword) ;

   Department addDepartment(Department department);

   Department updateDepartment(Department department) ;


}