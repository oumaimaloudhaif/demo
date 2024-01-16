package com.example.demo.ServicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.DemoApplication;
import com.example.demo.Dto.DepartmentDTO;
import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Entities.Department;
import com.example.demo.Entities.Employee;
import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;
import com.example.demo.Repository.DepartmentRepository;
import java.util.Arrays;
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
public class DepartmentServiceImplTest {
  @MockBean private DepartmentRepository departmentRepository;
  @Autowired private DepartmentServiceImpl departmentService;
  @Mock private FromDOToDTO fromDOToDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllDepartments() {
    // Given
    List<Employee> employees =
        Arrays.asList(
            new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI),
            new Employee(2L, "Oumaima", 1200, Gender.FEMALE, ContractType.CDI));
    List<Department> mockedDepartments =
        Arrays.asList(
            new Department("Department1", employees), new Department("Department2", employees));
    // When
    when(departmentRepository.findAll()).thenReturn(mockedDepartments);
    List<DepartmentDTO> Departments = departmentService.getAllDepartments();

    // Then
    assertEquals(mockedDepartments.size(), Departments.size());
  }

  @Test
  public void testSearchDepartments() {
    // Given

    final String keyword = "Oumaima";
    List<Employee> employees =
        Arrays.asList(
            new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI),
            new Employee(2L, "Oumaima", 1200, Gender.FEMALE, ContractType.CDI));
    List<Department> mockedDepartments =
        Arrays.asList(
            new Department("Department1", employees), new Department("Department2", employees));
    when(departmentRepository.findByName(keyword)).thenReturn(mockedDepartments);
    List<DepartmentDTO> Departments = departmentService.searchDepartment(keyword);
    assertEquals(mockedDepartments.size(), Departments.size());
  }

  @Test
  public void testAddDepartment() {
    // Given

    List<Employee> employees =
        Arrays.asList(
            new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI),
            new Employee(2L, "Oumaima", 1200, Gender.FEMALE, ContractType.CDI));
    Department inputDepartment = new Department("Department1", employees);
    Department savedDepartment = new Department("Department1", employees);
    DepartmentDTO expectedDepartmentDTO = new DepartmentDTO("Department1", employees);

    when(departmentRepository.save(inputDepartment)).thenReturn(savedDepartment);
    when(fromDOToDTO.MapDepartment(savedDepartment)).thenReturn(expectedDepartmentDTO);

    DepartmentDTO resultDepartmentDTO = departmentService.addDepartment(inputDepartment);

    assertEquals(expectedDepartmentDTO, resultDepartmentDTO);
  }

  @Test
  public void testUpdateDepartment() {
    // Given

    List<Employee> employees =
        Arrays.asList(
            new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI),
            new Employee(2L, "Oumaima", 1200, Gender.FEMALE, ContractType.CDI));
    Department inputDepartment = new Department("Department1", employees);
    Department savedDepartment = new Department("Department1", employees);
    DepartmentDTO expectedDepartmentDTO = new DepartmentDTO("Department1", employees);

    when(departmentRepository.save(inputDepartment)).thenReturn(savedDepartment);
    when(fromDOToDTO.MapDepartment(savedDepartment)).thenReturn(expectedDepartmentDTO);

    DepartmentDTO resultDepartmentDTO = departmentService.addDepartment(inputDepartment);

    assertEquals(expectedDepartmentDTO, resultDepartmentDTO);
  }
}
