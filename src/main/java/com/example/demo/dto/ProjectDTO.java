package com.example.demo.dto;

import com.example.demo.entities.Employee;
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
