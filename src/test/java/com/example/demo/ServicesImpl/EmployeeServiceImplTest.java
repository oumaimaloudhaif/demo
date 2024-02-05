package com.example.demo.ServicesImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.demo.DemoApplication;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.Mappers.FromDOToDTO;
import com.example.demo.entities.Employee;
import com.example.demo.enums.ContractType;
import com.example.demo.enums.Gender;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.tools.EmployeeDTOTools;
import com.example.demo.tools.EmployeeTools;
import java.time.LocalDate;
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
public class EmployeeServiceImplTest {
  @MockBean private EmployeeRepository employeeRepository;

  @Autowired private EmployeeServiceImpl employeeService;
  @MockBean private FromDOToDTO fromDOToDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllEmployees() {
    // Given
    final Employee employee1 =
        EmployeeTools.createEmployee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    final Employee employee2 =
        EmployeeTools.createEmployee(2L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDD);
    final EmployeeDTO employee1DTO =
        EmployeeDTOTools.createEmployeeDTO("Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    final EmployeeDTO employee2DTO =
        EmployeeDTOTools.createEmployeeDTO("Oumaima L", 1000, Gender.FEMALE, ContractType.CDD);
    final List<Employee> mockedEmployees = Arrays.asList(employee1, employee2);

    // When
    when(employeeRepository.findAll()).thenReturn(mockedEmployees);
    when(fromDOToDTO.MapEmployee(employee1)).thenReturn(employee1DTO);
    when(fromDOToDTO.MapEmployee(employee2)).thenReturn(employee2DTO);
    final List<EmployeeDTO> employees = employeeService.getAllEmployees();

    // Then
    assertEquals(mockedEmployees.size(), employees.size());
  }

  @Test
  public void testSearchEmployees() {
    // Given
    final String keyword = "Oumaima";
    final Employee employee1 =
        EmployeeTools.createEmployee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    final Employee employee2 =
        EmployeeTools.createEmployee(2L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    final EmployeeDTO employee1DTO =
        EmployeeDTOTools.createEmployeeDTO("Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    final EmployeeDTO employee2DTO =
        EmployeeDTOTools.createEmployeeDTO("Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    final List<Employee> mockedEmployees = Arrays.asList(employee1, employee2);

    // When
    when(employeeRepository.findByNameContaining(keyword)).thenReturn(mockedEmployees);
    when(fromDOToDTO.MapEmployee(employee1)).thenReturn(employee1DTO);
    when(fromDOToDTO.MapEmployee(employee2)).thenReturn(employee2DTO);
    final List<EmployeeDTO> employees = employeeService.searchEmployees(keyword);

    // Then
    assertEquals(mockedEmployees.size(), employees.size());
  }

  @Test
  public void testGetExperiencedEmployees() {
    // Given
    final int yearsOfExperience = 5;
    final Employee employee1 =
        EmployeeTools.createEmployee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    final Employee employee2 =
        EmployeeTools.createEmployee(2L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    final List<Employee> mockedEmployees = Arrays.asList(employee1, employee2);

    // When
    mockedEmployees.get(0).setJoiningDate(LocalDate.now().minusYears(6));
    mockedEmployees.get(1).setJoiningDate(LocalDate.now().minusYears(3));
    when(employeeRepository.findAll()).thenReturn(mockedEmployees);
    final List<Employee> experiencedEmployees =
        employeeService.getExperiencedEmployees(yearsOfExperience);

    // Then
    assertEquals(1, experiencedEmployees.size());
  }

  @Test
  public void testFilterEmployeesByAge() {
    // Given
    final int minAge = 25;
    final int maxAge = 35;
    Employee employee1 =
        EmployeeTools.createEmployee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    Employee employee2 =
        EmployeeTools.createEmployee(2L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    final List<Employee> mockedEmployees = Arrays.asList(employee1, employee2);

    // When
    mockedEmployees.get(0).setDateOfBirth(LocalDate.now().minusYears(30));
    mockedEmployees.get(1).setDateOfBirth(LocalDate.now().minusYears(40));
    when(employeeRepository.findAll()).thenReturn(mockedEmployees);
    final List<Employee> filteredEmployees = employeeService.filterEmployeesByAge(minAge, maxAge);

    // Then
    assertEquals(1, filteredEmployees.size());
  }
}
