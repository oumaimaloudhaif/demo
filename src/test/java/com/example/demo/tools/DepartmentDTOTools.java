package com.example.demo.tools;

import com.example.demo.Dto.DepartmentDTO;
import org.springframework.stereotype.Component;


@Component
public class DepartmentDTOTools {
  public static DepartmentDTO createDepartmentDTO( String name) {
    return new DepartmentDTO().withName(name);
  }

  public static DepartmentDTO createDepartmentDTO() {
    return new DepartmentDTO().withName("Department");
  }
}
