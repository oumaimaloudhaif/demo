package com.example.demo.Services;

import com.example.demo.Entities.Company;
import com.example.demo.Entities.Department;

import java.util.List;

/**
 * Company Service
 */
public interface CompanyService {
    boolean isSkillDiverse(String skill);
    Department getMostOccupiedDepartment() ;
    List<Company> searchCompany(String keyword);
    Company addCompany(Company company);
    Company updateCompany(Company company);
    List<Company> getAllCompanies();

}
