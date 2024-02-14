package com.example.demo.tools;

import com.example.demo.dto.DepartmentDTO;
import org.springframework.stereotype.Component;

/** Department DTO Tools */
@Component
public class DepartmentDTOTools {
  public static DepartmentDTO createDepartmentDTO(String name) {

    return new DepartmentDTO().withName(name);
  }
}
