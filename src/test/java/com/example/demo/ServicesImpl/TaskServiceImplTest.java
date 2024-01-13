package com.example.demo.ServicesImpl;

import com.example.demo.DemoApplication;
import com.example.demo.Dto.EmployeeDTO;
import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Entities.Employee;
import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;
import com.example.demo.Repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DemoApplication.class)
@AutoConfigureMockMvc
public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    @Mock
    private FromDOToDTO fromDOToDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> mockedEmployees = Arrays.asList(
                new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI),
                new Employee(2L, "Oumaima", 1200, Gender.FEMALE, ContractType.CDI)
        );
        when(employeeRepository.findAll()).thenReturn(mockedEmployees);
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        assertEquals(mockedEmployees.size(), employees.size());
    }
    @Test
    public void testSearchEmployees() {
        String keyword = "Oumaima";
        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI));
        mockEmployees.add(new Employee(2L, "Oumaima", 1200, Gender.FEMALE, ContractType.CDI));
        when(employeeRepository.findByNameContaining(keyword)).thenReturn(mockEmployees);
        List<EmployeeDTO> employees = employeeService.searchEmployees(keyword);
        assertEquals(mockEmployees.size(), employees.size());
    }
    @Test
    public void testGetExperiencedEmployees() {
        int yearsOfExperience = 5;
        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(new Employee(1L, "oumaima", 1000, Gender.FEMALE, ContractType.CDI));
        mockEmployees.add(new Employee(2L, "mayssa", 1200, Gender.FEMALE, ContractType.CDI));
        mockEmployees.get(0).setJoiningDate(LocalDate.now().minusYears(6));
        mockEmployees.get(1).setJoiningDate(LocalDate.now().minusYears(3));

        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        List<Employee> experiencedEmployees = employeeService.getExperiencedEmployees(yearsOfExperience);
        assertEquals(1, experiencedEmployees.size());
    }
    @Test
    public void testFilterEmployeesByAge() {
        int minAge = 25;
        int maxAge = 35;
        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(new Employee(1L, "Oumaima", 1000, Gender.FEMALE, ContractType.CDI));
        mockEmployees.add(new Employee(2L, "Oumaima l", 1200, Gender.FEMALE, ContractType.CDI));
        mockEmployees.get(0).setDateOfBirth(LocalDate.now().minusYears(30));
        mockEmployees.get(1).setDateOfBirth(LocalDate.now().minusYears(40));
        when(employeeRepository.findAll()).thenReturn(mockEmployees);
        List<Employee> filteredEmployees = employeeService.filterEmployeesByAge(minAge, maxAge);
        assertEquals(1, filteredEmployees.size());
     }


}