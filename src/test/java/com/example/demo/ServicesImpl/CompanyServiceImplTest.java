package com.example.demo.ServicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.DemoApplication;
import com.example.demo.Dto.CompanyDTO;
import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Entities.Company;
import com.example.demo.Entities.Department;
import com.example.demo.Entities.Employee;
import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;
import com.example.demo.Repository.CompanyRepository;
import com.example.demo.tools.CompanyTools;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
  @Mock private FromDOToDTO fromDOToDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllCompanies() {
    // Given
    final Date date = new Date(2024, Calendar.JANUARY, 13);
    final List<Employee> employees =
        Arrays.asList(
            new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI),
            new Employee(2L, "Oumaima", 1200, Gender.FEMALE, ContractType.CDI));
    final List<Department> departments =
        Arrays.asList(
            new Department("Department1", employees), new Department("Department2", employees));
    final Company firstCompany = CompanyTools.createCompany(1L, "Company1", departments);
    final Company secondCompany = CompanyTools.createCompany(1L, "Company1", departments);
    final List<Company> mockedCompanies = Arrays.asList(firstCompany, secondCompany);

    final CompanyDTO firstCompanyDto = new CompanyDTO("Company1");
    final CompanyDTO secondCompanyDto = new CompanyDTO("Company2");
    // When

    when(companyRepository.findAll()).thenReturn(mockedCompanies);
    when(fromDOToDTO.MapCompany(firstCompany)).thenReturn(firstCompanyDto);
    when(fromDOToDTO.MapCompany(secondCompany)).thenReturn(secondCompanyDto);

    List<CompanyDTO> companies = companyService.getAllCompanies();

    // Then
    assertEquals(mockedCompanies.size(), companies.size());
  }

  @Test
  public void testSearchCompanies() {
    // Given
    Date date = new Date(2024, Calendar.JANUARY, 13);
    final String keyword = "Oumaima";
    final List<Employee> employees =
        Arrays.asList(
            new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI),
            new Employee(2L, "Oumaima", 1200, Gender.FEMALE, ContractType.CDI));
    final List<Department> departments =
        Arrays.asList(
            new Department("Department1", employees), new Department("Department2", employees));
    final Company firstCompany = CompanyTools.createCompany(1L, "Company1", departments);
    final Company secondCompany = CompanyTools.createCompany(1L, "Company1", departments);
    final List<Company> mockedCompanies = Arrays.asList(firstCompany, secondCompany);
    final CompanyDTO firstCompanyDto = new CompanyDTO("Company1");
    final CompanyDTO secondCompanyDto = new CompanyDTO("Company2");
    when(fromDOToDTO.MapCompany(firstCompany)).thenReturn(firstCompanyDto);
    when(fromDOToDTO.MapCompany(secondCompany)).thenReturn(secondCompanyDto);

    // When
    when(companyRepository.findByName(keyword)).thenReturn(mockedCompanies);
    List<CompanyDTO> companies = companyService.searchCompany(keyword);

    // Then
    assertEquals(mockedCompanies.size(), companies.size());
  }
  /*    @Test
   public void testAddCompany() {
       Date date=new Date(2024, Calendar.JANUARY,13);
       List<Employee> employees = Arrays.asList(
               new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI),
               new Employee(2L, "Oumaima", 1200, Gender.FEMALE, ContractType.CDI)
       );
       List<Department> departments = Arrays.asList(
               new Department("Department1",employees),
               new Department("Department2",employees)
       );
       Company inputCompany =  new Company(1L,"Company1", departments, date, date);
       Company savedCompany =  new Company(2L,"Company1", departments, date, date);
       CompanyDTO expectedCompanyDTO = new CompanyDTO("Company1");

       when(companyRepository.save(inputCompany)).thenReturn(savedCompany);
       when(fromDOToDTO.MapCompany(savedCompany)).thenReturn(expectedCompanyDTO);

       CompanyDTO resultCompanyDTO = companyService.addCompany(inputCompany);

       assertEquals(expectedCompanyDTO, resultCompanyDTO);
   }
   @Test
  public void testUpdateCompany() {
       Date date=new Date(2024, Calendar.JANUARY,13);
       List<Employee> employees = Arrays.asList(
               new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI),
               new Employee(2L, "Oumaima", 1200, Gender.FEMALE, ContractType.CDI)
       );
       List<Department> departments = Arrays.asList(
               new Department("Department1",employees),
               new Department("Department2",employees)
       );
       Company inputCompany =  new Company(1L,"Company1", departments, date, date);
       Company updteddCompany =  new Company(2L,"Company1", departments, date, date);
       CompanyDTO expectedCompanyDTO = new CompanyDTO("Company1");

       when(companyRepository.save(inputCompany)).thenReturn(updteddCompany);
       when(fromDOToDTO.MapCompany(updteddCompany)).thenReturn(expectedCompanyDTO);

       CompanyDTO resultCompanyDTO = companyService.addCompany(inputCompany);

       assertEquals(expectedCompanyDTO, resultCompanyDTO);
   }*/
}
