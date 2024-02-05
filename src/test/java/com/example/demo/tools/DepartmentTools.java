package com.example.demo.tools;

import com.example.demo.entities.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentTools {
  public static Department createDepartment(Long id, String name) {

    return new Department().withDepartment_id(id).withName(name);
  }
}
