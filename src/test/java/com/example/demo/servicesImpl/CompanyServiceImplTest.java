package com.example.demo.servicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.DemoApplication;
import com.example.demo.dto.CompanyDTO;
import com.example.demo.dto.mappers.FromDOToDTO;
import com.example.demo.entities.Company;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.tools.CompanyDTOTools;
import com.example.demo.tools.CompanyTools;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = DemoApplication.class)
@AutoConfigureMockMvc
public class CompanyServiceImplTest {
  @MockBean private CompanyRepository companyRepository;
  @Autowired private CompanyServiceImpl companyService;
  @MockBean private FromDOToDTO fromDOToDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllCompanies() {
    // Given
    final Company firstCompany = CompanyTools.createCompany(1L, "Company1");
    final Company secondCompany = CompanyTools.createCompany(1L, "Company1");
    final List<Company> mockedCompanies = Arrays.asList(firstCompany, secondCompany);

    final CompanyDTO firstCompanyDto = CompanyDTOTools.createCompanyDTO("Company1");
    final CompanyDTO secondCompanyDto = CompanyDTOTools.createCompanyDTO("Company2");

    // When
    when(companyRepository.findAll()).thenReturn(mockedCompanies);
    when(fromDOToDTO.MapCompany(firstCompany)).thenReturn(firstCompanyDto);
    when(fromDOToDTO.MapCompany(secondCompany)).thenReturn(secondCompanyDto);

    final List<CompanyDTO> companies = companyService.getAllCompanies();
    // Then

    assertEquals(mockedCompanies.size(), companies.size());
  }

  @Test
  public void testSearchCompanies() {
    // Given
    final String keyword = "Oumaima";
    final Company firstCompany = CompanyTools.createCompany(1L, "Company1");
    final Company secondCompany = CompanyTools.createCompany(2L, "Company1");
    final List<Company> mockedCompanies = Arrays.asList(firstCompany, secondCompany);
    final CompanyDTO firstCompanyDto = CompanyDTOTools.createCompanyDTO("Company1");
    final CompanyDTO secondCompanyDto = CompanyDTOTools.createCompanyDTO("Company2");
    when(fromDOToDTO.MapCompany(firstCompany)).thenReturn(firstCompanyDto);
    when(fromDOToDTO.MapCompany(secondCompany)).thenReturn(secondCompanyDto);

    // When
    when(companyRepository.findByName(keyword)).thenReturn(mockedCompanies);
    final List<CompanyDTO> companies = companyService.searchCompany(keyword);

    // Then
    assertEquals(mockedCompanies.size(), companies.size());
  }

  @Test
  public void testAddCompany() {
    // Given
    final Company inputCompany = CompanyTools.createCompany(1L, "Company1");
    final CompanyDTO expectedCompanyDTO = CompanyDTOTools.createCompanyDTO("Company1");

    // When
    when(companyRepository.save(inputCompany)).thenReturn(inputCompany);
    when(fromDOToDTO.MapCompany(inputCompany)).thenReturn(expectedCompanyDTO);

    final CompanyDTO resultCompanyDTO = companyService.addCompany(inputCompany);

    // Then
    assertEquals(expectedCompanyDTO.getName(), resultCompanyDTO.getName());
  }

  @Test
  public void testUpdateCompany() {
    // Given
    final Company inputCompany = CompanyTools.createCompany(1L, "Company1");
    final CompanyDTO expectedCompanyDTO = CompanyDTOTools.createCompanyDTO("Company1");

    // When
    when(companyRepository.save(inputCompany)).thenReturn(inputCompany);
    when(fromDOToDTO.MapCompany(inputCompany)).thenReturn(expectedCompanyDTO);

    final CompanyDTO resultCompanyDTO = companyService.addCompany(inputCompany);

    // Then
    assertEquals(expectedCompanyDTO.getName(), resultCompanyDTO.getName());
  }
}
