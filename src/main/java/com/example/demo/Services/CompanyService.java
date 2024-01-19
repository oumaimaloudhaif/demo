package com.example.demo.Services;

import com.example.demo.Dto.CompanyDTO;
import com.example.demo.Dto.DepartmentDTO;
import com.example.demo.Entities.Company;
import com.example.demo.Entities.Department;
import java.util.List;

/** Company Service */
public interface CompanyService {
  boolean isSkillDiverse(String skill);

  DepartmentDTO getMostOccupiedDepartment();

  List<CompanyDTO> searchCompany(String keyword);

  CompanyDTO addCompany(Company company);

  CompanyDTO updateCompany(Company company);

  List<CompanyDTO> getAllCompanies();
}
