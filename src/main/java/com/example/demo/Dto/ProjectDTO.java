package com.example.demo.Dto;

import com.example.demo.Entities.Employee;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
  private String name;
  private List<Employee> employees = new ArrayList<>();

  public ProjectDTO(String project) {
    this.name = project;
  }
}
