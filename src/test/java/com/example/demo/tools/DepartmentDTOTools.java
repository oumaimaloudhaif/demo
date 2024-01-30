package com.example.demo.tools;

import com.example.demo.Dto.DepartmentDTO;
import com.example.demo.Entities.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentDTOTools {
  public static DepartmentDTO createDepartmentDTO(String name, List<Employee> employees) {
    return new DepartmentDTO(name,employees);
  }

}
