package com.example.demo.tools;

import com.example.demo.Dto.EmployeeDTO;
import com.example.demo.Entities.Address;
import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class EmployeeDTOTools {
  public static EmployeeDTO createEmployeeDTO(String name, int salary, Address address, Gender gender, ContractType contractType) {
    return new EmployeeDTO(name,salary, LocalDate.now(),address,LocalDate.now(),gender,contractType);
  }
}
