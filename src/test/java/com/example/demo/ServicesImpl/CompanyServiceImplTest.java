package com.example.demo.ServicesImpl;

import com.example.demo.DemoApplication;
import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Dto.CompanyDTO;
import com.example.demo.Entities.Company;
import com.example.demo.Entities.Department;
import com.example.demo.Entities.Employee;
import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;
import com.example.demo.Repository.CompanyRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DemoApplication.class)
@AutoConfigureMockMvc
public class CompanyServiceImplTest {
    @Mock
    private CompanyRepository companyRepository;
    @InjectMocks
    private CompanyServiceImpl companyService;
    @Mock
    private FromDOToDTO fromDOToDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    Date date=new Date(2024, Calendar.JANUARY,13);
    @Test
    public void testGetAllCompanys() {
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI),
                new Employee(2L, "Oumaima", 1200, Gender.FEMALE, ContractType.CDI)
        );
        List<Department> departments = Arrays.asList(
                new Department("Department1",employees),
                new Department("Department2",employees)
        );
        List<Company> mockedCompanys = Arrays.asList(
                new Company(1L,"Company1", departments, date, date),
                new Company(2L,"Company2",departments, date, date)
        );
        when(companyRepository.findAll()).thenReturn(mockedCompanys);
        List<CompanyDTO> Companys = companyService.getAllCompanies();
        assertEquals(mockedCompanys.size(), Companys.size());
    }
    @Test
    public void testSearchCompanys() {
        String keyword = "Oumaima";
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI),
                new Employee(2L, "Oumaima", 1200, Gender.FEMALE, ContractType.CDI)
        );
        List<Department> departments = Arrays.asList(
                new Department("Department1",employees),
                new Department("Department2",employees)
        );
        List<Company> mockedCompanys = Arrays.asList(
                new Company(1L,"Company1", departments, date, date),
                new Company(2L,"Company2",departments, date, date)
        );
        when(companyRepository.findByName(keyword)).thenReturn(mockedCompanys);
        List<CompanyDTO> Companys = companyService.searchCompany(keyword);
        assertEquals(mockedCompanys.size(), Companys.size());
    }
    @Test
    public void testAddCompany() {
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
    }
}