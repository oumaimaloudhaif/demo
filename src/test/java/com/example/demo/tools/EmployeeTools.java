package com.example.demo.tools;

import com.example.demo.Entities.Employee;
import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;
import org.springframework.stereotype.Component;


@Component
public class EmployeeTools {
  public static Employee createEmployee(Long id, String name, int salary, Gender gender, ContractType contractType) {
    return new Employee().withEmployee_id(id).withName(name).withSalary(salary).withGender(gender).withContractType(contractType);
  }
}
