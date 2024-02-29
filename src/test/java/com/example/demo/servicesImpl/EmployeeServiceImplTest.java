package com.example.demo.servicesImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.demo.DemoApplication;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.mappers.FromDOToDTO;
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
    when(fromDOToDTO.mapEmployee(employee1)).thenReturn(employee1DTO);
    when(fromDOToDTO.mapEmployee(employee2)).thenReturn(employee2DTO);
    final List<EmployeeDTO> employees = employeeService.getAllEmployees();

    // Then
    assertEquals(mockedEmployees.size(), employees.size());
    assertEquals("Oumaima L", employees.get(0).getName());
    assertEquals("Oumaima L", employees.get(1).getName());
    assertEquals(1000, employees.get(0).getSalary());
    assertEquals(1000, employees.get(1).getSalary());
    assertEquals(Gender.FEMALE, employees.get(0).getGender());
    assertEquals(Gender.FEMALE, employees.get(1).getGender());
    assertEquals(ContractType.CDI, employees.get(0).getContractType());
    assertEquals(ContractType.CDD, employees.get(1).getContractType());
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
    when(fromDOToDTO.mapEmployee(employee1)).thenReturn(employee1DTO);
    when(fromDOToDTO.mapEmployee(employee2)).thenReturn(employee2DTO);
    final List<EmployeeDTO> employees = employeeService.searchEmployees(keyword);

    // Then
    assertEquals(mockedEmployees.size(), employees.size());
    assertEquals("Oumaima L", employees.get(0).getName());
    assertEquals("Oumaima L", employees.get(1).getName());
    assertEquals(1000, employees.get(0).getSalary());
    assertEquals(1000, employees.get(1).getSalary());
    assertEquals(Gender.FEMALE, employees.get(0).getGender());
    assertEquals(Gender.FEMALE, employees.get(1).getGender());
    assertEquals(ContractType.CDI, employees.get(0).getContractType());
    assertEquals(ContractType.CDI, employees.get(1).getContractType());
  }

  @Test
  public void testGetExperiencedEmployees() {
    // Given
    final int yearsOfExperience = 5;
    final Employee employee1 =
        EmployeeTools.createEmployee(1L, "Oumaima L", 7000, Gender.FEMALE, ContractType.CDI);
    final Employee employee2 =
        EmployeeTools.createEmployee(2L, "Oumaima L1", 1000, Gender.FEMALE, ContractType.CDD);
    final List<Employee> mockedEmployees = Arrays.asList(employee1, employee2);

    // When
    mockedEmployees.get(0).setJoiningDate(LocalDate.now().minusYears(6));
    mockedEmployees.get(1).setJoiningDate(LocalDate.now().minusYears(3));
    when(employeeRepository.findAll()).thenReturn(mockedEmployees);
    final List<Employee> experiencedEmployees =
        employeeService.getExperiencedEmployees(yearsOfExperience);

    // Then
    assertEquals(1, experiencedEmployees.size());
    assertEquals("Oumaima L", experiencedEmployees.get(0).getName());
    assertEquals(7000, experiencedEmployees.get(0).getSalary());
    assertEquals(Gender.FEMALE, experiencedEmployees.get(0).getGender());
    assertEquals(ContractType.CDI, experiencedEmployees.get(0).getContractType());
  }

  @Test
  public void testFilterEmployeesByAge() {
    // Given
    final int minAge = 25;
    final int maxAge = 35;
    final Employee employee1 =
        EmployeeTools.createEmployee(1L, "Oumaima L", 7000, Gender.FEMALE, ContractType.CDI);
    final Employee employee2 =
        EmployeeTools.createEmployee(2L, "Oumaima L1", 1000, Gender.FEMALE, ContractType.CDD);
    final List<Employee> mockedEmployees = Arrays.asList(employee1, employee2);

    // When
    mockedEmployees.get(0).setDateOfBirth(LocalDate.now().minusYears(30));
    mockedEmployees.get(1).setDateOfBirth(LocalDate.now().minusYears(40));
    when(employeeRepository.findAll()).thenReturn(mockedEmployees);
    final List<Employee> filteredEmployees = employeeService.filterEmployeesByAge(minAge, maxAge);

    // Then
    assertEquals(1, filteredEmployees.size());
    assertEquals(1, filteredEmployees.size());
    assertEquals("Oumaima L", filteredEmployees.get(0).getName());
    assertEquals(7000, filteredEmployees.get(0).getSalary());
    assertEquals(Gender.FEMALE, filteredEmployees.get(0).getGender());
    assertEquals(ContractType.CDI, filteredEmployees.get(0).getContractType());
  }

  @Test
  public void testGetEmployeeExist() {
    // Given
    final long employee_Id = 1L;
    final Employee employee =
        EmployeeTools.createEmployee(
            employee_Id, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    final EmployeeDTO employeeDTO =
        EmployeeDTOTools.createEmployeeDTO("Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    // When
    when(employeeRepository.findById(employee_Id)).thenReturn(java.util.Optional.of(employee));
    when(fromDOToDTO.mapEmployee(employee)).thenReturn(employeeDTO);
    final EmployeeDTO resultAddressDTO = employeeService.getEmployeeById(employee_Id);

    // Then
    assertEquals("Oumaima L", resultAddressDTO.getName());
    assertEquals(1000, resultAddressDTO.getSalary());
    assertEquals(Gender.FEMALE, resultAddressDTO.getGender());
    assertEquals(ContractType.CDI, resultAddressDTO.getContractType());
  }

  @Test
  public void testGetEmployeeNonExist() {
    // Given
    final long employee_Id = 1L;

    // When
    when(employeeRepository.getById(employee_Id)).thenReturn(null);
    final EmployeeDTO resultEmployeeDTO = employeeService.getEmployeeById(employee_Id);

    // Then
    assertNull(resultEmployeeDTO);
  }

  @Test
  public void testDeleteEmployeeExist() {
    // Given
    final long employee_Id = 1L;
    final Employee employee =
        EmployeeTools.createEmployee(
            employee_Id, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);

    // When
    when(employeeRepository.findById(employee_Id)).thenReturn(java.util.Optional.of(employee));
    final String resultEmployeeDTO = employeeService.deleteEmployee(employee_Id);

    // Then
    assertEquals("Employee is deleted successfully", resultEmployeeDTO);
  }

  @Test
  public void testDeleteEmployeeNonExist() {
    // Given
    final long employee_Id = 1L;

    // When
    when(employeeRepository.getById(employee_Id)).thenReturn(null);
    final String resultEmployeeDTO = employeeService.deleteEmployee(employee_Id);

    // Then
    assertEquals("Employee not found", resultEmployeeDTO);
  }
}
