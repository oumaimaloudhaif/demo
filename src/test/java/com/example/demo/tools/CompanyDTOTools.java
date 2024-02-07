package com.example.demo.tools;

import com.example.demo.dto.CompanyDTO;
import org.springframework.stereotype.Component;

/** Company DTO Tools */
@Component
public class CompanyDTOTools {
  public static CompanyDTO createCompanyDTO(String name) {

    return new CompanyDTO().withName(name);
  }
}
