package com.example.demo.tools;

import com.example.demo.entities.Company;
import org.springframework.stereotype.Component;

/** Company Tools */
@Component
public class CompanyTools {
  public static Company createCompany(Long id, String name) {

    return new Company().withCompany_id(id).withName(name);
  }
}
