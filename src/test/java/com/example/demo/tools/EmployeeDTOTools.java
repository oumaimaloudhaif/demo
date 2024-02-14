package com.example.demo.tools;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.enums.ContractType;
import com.example.demo.enums.Gender;
import org.springframework.stereotype.Component;

/** Employee DTO Tools */
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
