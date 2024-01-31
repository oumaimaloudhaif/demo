package com.example.demo.tools;

import com.example.demo.Entities.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentTools {
  public static Department createDepartment(Long id, String name) {
    return new Department().withDepartment_id(id).withName(name);
  }

  public static Department createDepartment() {
    return new Department().withDepartment_id(1L).withName("Department");
  }
}
