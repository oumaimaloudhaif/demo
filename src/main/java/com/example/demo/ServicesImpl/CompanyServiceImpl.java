package com.example.demo.ServicesImpl;

import com.example.demo.Dto.CompanyDTO;
import com.example.demo.Dto.DepartmentDTO;
import com.example.demo.Dto.EmployeeDTO;
import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Entities.Company;
import com.example.demo.Repository.CompanyRepository;
import com.example.demo.Services.CompanyService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Company Service Implementation */
@Service
public class CompanyServiceImpl implements CompanyService {
  @Autowired private EmployeeServiceImpl employeeServiceImpl;
  @Autowired private DepartmentServiceImpl departmentServiceImpl;
  @Autowired private CompanyRepository companyRepository;
  @Autowired private FromDOToDTO fromDOToDTO;

  public DepartmentDTO getMostOccupiedDepartment() {
    List<DepartmentDTO> departments = departmentServiceImpl.getAllDepartments();
    return departments
        .stream()
        .max(Comparator.comparingInt(departmentDTO -> departmentDTO.getEmployees().size()))
        .orElse(null);
  }

  @Override
  public List<CompanyDTO> searchCompany(String keyword) {
    List<Company> companies = companyRepository.findByName(keyword);
    List<CompanyDTO> companyDTOS = new ArrayList<>();
    companies.forEach(
        company -> {
          CompanyDTO companyDTO = fromDOToDTO.MapCompany(company);
          companyDTOS.add(companyDTO);
        });
    return companyDTOS;
  }

  @Override
  public CompanyDTO addCompany(Company company) {
    Company savedCompany = companyRepository.save(company);
    return fromDOToDTO.MapCompany(savedCompany);
  }

  @Override
  public CompanyDTO updateCompany(Company company) {
    Company updatedAddress = companyRepository.save(company);
    return fromDOToDTO.MapCompany(updatedAddress);
  }

  @Override
  public List<CompanyDTO> getAllCompanies() {
    List<Company> companies = companyRepository.findAll();
    List<CompanyDTO> companyDTOS = new ArrayList<>();
    companies.forEach(
        company -> {
          CompanyDTO companyDTO = fromDOToDTO.MapCompany(company);
          companyDTOS.add(companyDTO);
        });
    return companyDTOS;
  }
}
