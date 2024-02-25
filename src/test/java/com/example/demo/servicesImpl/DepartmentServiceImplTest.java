package com.example.demo.servicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.DemoApplication;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.mappers.FromDOToDTO;
import com.example.demo.entities.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.tools.DepartmentDTOTools;
import com.example.demo.tools.DepartmentTools;
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
public class DepartmentServiceImplTest {
  @MockBean private DepartmentRepository departmentRepository;
  @Autowired private DepartmentServiceImpl departmentService;
  @MockBean private FromDOToDTO fromDOToDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllDepartments() {
    // Given
    final Department department1 = DepartmentTools.createDepartment(1L, "Department1");
    final Department department2 = DepartmentTools.createDepartment(2L, "Department2");
    final List<Department> mockedDepartments = Arrays.asList(department1, department2);

    // When
    when(departmentRepository.findAll()).thenReturn(mockedDepartments);
    final List<DepartmentDTO> Departments = departmentService.getAllDepartments();

    // Then
    assertEquals(mockedDepartments.size(), Departments.size());
  }

  @Test
  public void testSearchDepartments() {
    // Given
    final String keyword = "Oumaima";
    final Department department1 = DepartmentTools.createDepartment(1L, "Department1");
    final Department department2 = DepartmentTools.createDepartment(2L, "Department2");
    final List<Department> mockedDepartments = Arrays.asList(department1, department2);

    // When
    when(departmentRepository.findByName(keyword)).thenReturn(mockedDepartments);
    final List<DepartmentDTO> Departments = departmentService.searchDepartment(keyword);

    // Then
    assertEquals(mockedDepartments.size(), Departments.size());
  }

  @Test
  public void testAddDepartment() {
    // Given
    Department inputDepartment = DepartmentTools.createDepartment(1L, "Department1");
    DepartmentDTO expectedDepartmentDTO = DepartmentDTOTools.createDepartmentDTO("Department1");

    // When
    when(departmentRepository.save(inputDepartment)).thenReturn(inputDepartment);
    when(fromDOToDTO.mapDepartment(inputDepartment)).thenReturn(expectedDepartmentDTO);
    DepartmentDTO resultDepartmentDTO = departmentService.addDepartment(inputDepartment);

    // Then
    assertEquals(expectedDepartmentDTO, resultDepartmentDTO);
  }

  @Test
  public void testUpdateDepartment() {
    Department inputDepartment = DepartmentTools.createDepartment(1L, "Department1");
    DepartmentDTO expectedDepartmentDTO = new DepartmentDTO("Department1");

    // When
    when(departmentRepository.save(inputDepartment)).thenReturn(inputDepartment);
    when(fromDOToDTO.mapDepartment(inputDepartment)).thenReturn(expectedDepartmentDTO);
    DepartmentDTO resultDepartmentDTO = departmentService.updateDepartment(inputDepartment);

    // Then
    assertEquals(expectedDepartmentDTO, resultDepartmentDTO);
  }
}
