package com.example.demo.Dto;

import com.example.demo.Entities.Address;
import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private String name;
    private float salary;
    private LocalDate dateOfBirth;
    private Address address;
    private LocalDate joiningDate;
    private Gender gender;
    private ContractType contractType;

    public EmployeeDTO( String name, float salary, Gender gender, ContractType contractType) {
        this.name=name;
        this.salary=salary;
        this.gender=gender;
        this.contractType=contractType;
    }
}
