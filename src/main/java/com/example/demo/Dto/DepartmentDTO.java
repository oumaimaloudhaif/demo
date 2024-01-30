package com.example.demo.Dto;

import com.example.demo.Entities.Employee;
import java.util.List;

public record DepartmentDTO(String name,List<Employee> employees) {

}
