package com.example.demo.servicesImpl;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.dto.mappers.FromDOToDTO;
import com.example.demo.entities.Company;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.services.CompanyService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Company Service Implementation */
@Service
public class CompanyServiceImpl implements CompanyService {
  @Autowired private CompanyRepository companyRepository;
  @Autowired private FromDOToDTO fromDOToDTO;

  /**
   * @param keyword a keyword (company name) to search for companies
   * @return List<CompanyDTO>
   */
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

  /**
   * @param company the company object to be added
   * @return CompanyDTO
   */
  @Override
  public CompanyDTO addCompany(Company company) {
    Company savedCompany = companyRepository.save(company);

    return fromDOToDTO.MapCompany(savedCompany);
  }

  /**
   * @param company the company object to be updated
   * @return CompanyDTO
   */
  @Override
  public CompanyDTO updateCompany(Company company) {
    Company updatedAddress = companyRepository.save(company);

    return fromDOToDTO.MapCompany(updatedAddress);
  }

  /** @return List<CompanyDTO> */
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
