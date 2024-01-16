package com.example.demo.DTO.Mappers;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.DemoApplication;
import com.example.demo.Dto.*;
import com.example.demo.Dto.Mappers.FromDTOToDO;
import com.example.demo.Entities.*;
import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;
import com.example.demo.Enums.Priority;
import com.example.demo.Enums.TaskStatus;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = DemoApplication.class)
class FromDTOToDOTest {
  FromDTOToDO mapper = new FromDTOToDO();

  @BeforeEach
  public void setUp() {}

  @Test
  void mapAddressDTO() {
    AddressDTO addressDto = new AddressDTO("Street", "City", "7034");

    Address result = mapper.MapAddressDTO(addressDto);

    assertEquals("Street", result.getStreet());
    assertEquals("City", result.getCity());
    assertEquals("7034", result.getPostalCode());
  }

  @Test
  void mapCompanyDTO() {
    CompanyDTO companyDTO = new CompanyDTO("Company Name");

    Company result = mapper.MapCompanyDTO(companyDTO);

    assertEquals("Company Name", result.getName());
  }

  @Test
  void mapDepartmentDTO() {
    Employee employee = new Employee(1L, "oumaima");
    List<Employee> employees = List.of(employee);
    DepartmentDTO departmentDTO = new DepartmentDTO("Informatique", employees);

    Department result = mapper.MapDepartmentDTO(departmentDTO);

    assertEquals("Informatique", result.getName());
    assertEquals(employees.size(), result.getEmployees().size());
  }

  @Test
  void mapEmployeeDTO() {
    EmployeeDTO employeeDTO = new EmployeeDTO("Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);

    Employee result = mapper.MapEmployeeDTO(employeeDTO);

    assertEquals("Oumaima L", result.getName());
    assertEquals(Gender.FEMALE, result.getGender());
    assertEquals(ContractType.CDI, result.getContractType());
    assertEquals(1000, result.getSalary());
  }

  @Test
  void mapMeetingDTO() {
    MeetingDTO meetingDTO = new MeetingDTO("meeting");

    Meeting result = mapper.MapMeetingDTO(meetingDTO);

    assertEquals("meeting", result.getTitle());
  }

  @Test
  void mapProject() {
    ProjectDTO projectDTO = new ProjectDTO("project");

    Project result = mapper.MapProjectDTO(projectDTO);

    assertEquals("project", result.getName());
  }

  @Test
  void mapReport() {
    ReportDTO reportDTO = new ReportDTO("report");

    Report result = mapper.MapReport(reportDTO);

    assertEquals("report", result.getTitle());
  }

  @Test
  void mapTask() {
    TaskDTO taskDTO = new TaskDTO("task", "description", Priority.HIGH, TaskStatus.IN_PROGRESS);

    Task result = mapper.MapTask(taskDTO);

    assertEquals("task", result.getName());
    assertEquals("description", result.getDescription());
    assertEquals(TaskStatus.IN_PROGRESS, result.getTaskStatus());
    assertEquals(Priority.HIGH, result.getPriority());
  }
}
