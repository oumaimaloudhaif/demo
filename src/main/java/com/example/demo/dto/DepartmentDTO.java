package com.example.demo.dto;

import com.example.demo.entities.Employee;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

/** Department DTO */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@With
public class DepartmentDTO {
  private String name;

  private List<Employee> employees = new ArrayList<>();

  public DepartmentDTO(String department1) {
    this.name = department1;
  }
}
