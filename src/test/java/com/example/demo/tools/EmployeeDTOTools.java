package com.example.demo.tools;

import com.example.demo.Dto.EmployeeDTO;
import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDTOTools {
  public static EmployeeDTO createEmployeeDTO(
      String name, int salary, Gender gender, ContractType contractType) {
    return new EmployeeDTO()
        .withName(name)
        .withSalary(salary)
        .withGender(gender)
        .withContractType(contractType);
  }
}
