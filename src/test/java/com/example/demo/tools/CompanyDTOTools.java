package com.example.demo.tools;

import com.example.demo.Dto.CompanyDTO;
import org.springframework.stereotype.Component;

@Component
public class CompanyDTOTools {
  public static CompanyDTO createCompanyDTO(String name) {
    return new CompanyDTO(name);
  }

}
