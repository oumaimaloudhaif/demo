package com.example.demo.Dto;

import com.example.demo.Entities.Address;
import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;
import java.time.LocalDate;

public record EmployeeDTO(String name, float salary, LocalDate dateOfBirth, Address address,LocalDate joiningDate, Gender gender,ContractType contractType){
}
