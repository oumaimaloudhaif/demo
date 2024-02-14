package com.example.demo.dto;

import com.example.demo.entities.Address;
import com.example.demo.enums.ContractType;
import com.example.demo.enums.Gender;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

/** Employee DTO */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@With
public class EmployeeDTO {
  private String name;

  private float salary;

  private LocalDate dateOfBirth;

  private Address address;

  private LocalDate joiningDate;

  private Gender gender;

  private ContractType contractType;

  public EmployeeDTO(String name, float salary, Gender gender, ContractType contractType) {
    this.name = name;
    this.salary = salary;
    this.gender = gender;
    this.contractType = contractType;
  }
}
