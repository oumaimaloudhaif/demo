package com.example.demo.controllers;

import com.example.demo.controllers.mappers.CompanyMapper;
import com.example.demo.controllers.request.CompanyRequest;
import com.example.demo.controllers.response.CompanyResponse;
import com.example.demo.dto.CompanyDTO;
import com.example.demo.entities.Company;
import com.example.demo.servicesImpl.CompanyServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** Company Controller */
@Validated
@RestController
public class CompanyController {
  @Autowired private CompanyServiceImpl companyServiceImpl;
  @Autowired private CompanyMapper companyMapper;

  /**
   * Adds a new company
   *
   * @param company the company object to be added
   * @return CompanyDTO
   */
  @PostMapping("/companies")
  public CompanyDTO addCompany(@RequestBody @Valid Company company) {

    return companyServiceImpl.addCompany(company);
  }

  /**
   * Updates an existing company
   *
   * @param company the company object to be updated
   * @return CompanyDTO
   */
  @PutMapping("/companies")
  public CompanyDTO updateCompany(@RequestBody @Valid Company company) {
    return companyServiceImpl.updateCompany(company);
  }

  /**
   * @param companyRequest the request object containing the keyword related to the company
   * @return CompanyResponse
   */
  @GetMapping("/companies")
  public CompanyResponse getCompanies(
      @RequestBody(required = false) @Valid CompanyRequest companyRequest) {
    if (companyRequest != null && !companyRequest.getKeyword().isBlank()) {
      return companyMapper.toCompanyResponse(
          companyServiceImpl.searchCompany(companyRequest.getKeyword()));
    } else {
      return companyMapper.toCompanyResponse(companyServiceImpl.getAllCompanies());
    }
  }

  /**
   * Retrieves a company by its ID and deletes it.
   *
   * @param companyId the ID of the company to delete
   * @return true if the company was successfully deleted, false otherwise
   */
  @DeleteMapping("/companies/{id}")
  public boolean deleteCompanyById(@PathVariable("id") Long companyId) {

    return companyServiceImpl.deleteCompanyById(companyId);
  }

  /**
   * Retrieves a company by its ID.
   *
   * @param companyId the ID of the company to retrieve
   * @return CompanyDTO corresponding to the company, or null if the company does not exist
   */
  @GetMapping("/companies/{id}")
  public CompanyDTO getCompanyById(@PathVariable("id") Long companyId) {

    return companyServiceImpl.getCompanyById(companyId);
  }
}
