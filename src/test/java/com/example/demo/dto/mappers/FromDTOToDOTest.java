package com.example.demo.dto.mappers;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.DemoApplication;
import com.example.demo.dto.*;
import com.example.demo.entities.*;
import com.example.demo.enums.ContractType;
import com.example.demo.enums.Gender;
import com.example.demo.enums.Priority;
import com.example.demo.enums.TaskStatus;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = DemoApplication.class)
class FromDTOToDOTest {
  @Autowired FromDTOToDO mapper;

  @BeforeEach
  public void setUp() {}

  @Test
  void mapAddressDTO() {
    final AddressDTO addressDto = new AddressDTO("Street", "City", "7034");

    final Address result = mapper.MapAddressDTO(addressDto);

    assertEquals("Street", result.getStreet());
    assertEquals("City", result.getCity());
    assertEquals("7034", result.getPostalCode());
  }

  @Test
  void mapCompanyDTO() {
    final CompanyDTO companyDTO = new CompanyDTO("Company Name");

    final Company result = mapper.MapCompanyDTO(companyDTO);

    assertEquals("Company Name", result.getName());
  }

  @Test
  void mapDepartmentDTO() {
    final Employee employee = new Employee(1L, "oumaima");
    final List<Employee> employees = List.of(employee);
    final DepartmentDTO departmentDTO = new DepartmentDTO("Info", employees);

    final Department result = mapper.MapDepartmentDTO(departmentDTO);

    assertEquals("Info", result.getName());
    assertEquals(employees.size(), result.getEmployees().size());
  }

  @Test
  void mapEmployeeDTO() {
    final EmployeeDTO employeeDTO =
        new EmployeeDTO("Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);

    final Employee result = mapper.MapEmployeeDTO(employeeDTO);

    assertEquals("Oumaima L", result.getName());
    assertEquals(Gender.FEMALE, result.getGender());
    assertEquals(ContractType.CDI, result.getContractType());
    assertEquals(1000, result.getSalary());
  }

  @Test
  void mapMeetingDTO() {
    final MeetingDTO meetingDTO = new MeetingDTO("meeting");

    final Meeting result = mapper.MapMeetingDTO(meetingDTO);

    assertEquals("meeting", result.getTitle());
  }

  @Test
  void mapProject() {
    final ProjectDTO projectDTO = new ProjectDTO("project");

    final Project result = mapper.MapProjectDTO(projectDTO);

    assertEquals("project", result.getName());
  }

  @Test
  void mapReport() {
    final ReportDTO reportDTO = new ReportDTO("report");

    final Report result = mapper.MapReport(reportDTO);

    assertEquals("report", result.getTitle());
  }

  @Test
  void mapTask() {
    final TaskDTO taskDTO =
        new TaskDTO("task", "description", Priority.HIGH, TaskStatus.IN_PROGRESS);

    final Task result = mapper.MapTask(taskDTO);

    assertEquals("task", result.getName());
    assertEquals("description", result.getDescription());
    assertEquals(TaskStatus.IN_PROGRESS, result.getTaskStatus());
    assertEquals(Priority.HIGH, result.getPriority());
  }
}
