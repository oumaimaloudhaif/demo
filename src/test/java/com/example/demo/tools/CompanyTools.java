package com.example.demo.tools;

import com.example.demo.Entities.Company;
import com.example.demo.Entities.Department;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CompanyTools {
  public static Company createCompany(Long id, String name, List<Department> department) {
    return new Company().withCompany_id(id).withName(name).withDepartments(department);
  }

  public static Company createCompany() {
    return new Company().withCompany_id(3L).withName("oumaima").withDepartments(List.of());
  }
}
