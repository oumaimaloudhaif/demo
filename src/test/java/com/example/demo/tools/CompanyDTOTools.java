package com.example.demo.tools;

import com.example.demo.Dto.CompanyDTO;
import com.example.demo.Entities.Company;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyDTOTools {
  public static CompanyDTO createCompanyDTO( String name) {
    return new CompanyDTO().withName(name);
  }
  public static CompanyDTO createCompanyDTO() {
    return new CompanyDTO().withName("oumaima");
  }
}
