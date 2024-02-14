package com.example.demo.services;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.entities.Company;
import java.util.List;

/** Company Service */
public interface CompanyService {

  List<CompanyDTO> searchCompany(String keyword);

  CompanyDTO addCompany(Company company);

  CompanyDTO updateCompany(Company company);

  List<CompanyDTO> getAllCompanies();
}
