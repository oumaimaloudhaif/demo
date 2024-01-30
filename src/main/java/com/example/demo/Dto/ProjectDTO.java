package com.example.demo.Dto;

import com.example.demo.Entities.Employee;
import java.util.List;

public record ProjectDTO (String name,List<Employee> employees ){
}
