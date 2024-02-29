package com.example.demo.dto.mappers;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.DemoApplication;
import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.CompanyDTO;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.MeetingDTO;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.dto.ReportDTO;
import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.WorkCalendarDTO;
import com.example.demo.entities.Address;
import com.example.demo.entities.Company;
import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Meeting;
import com.example.demo.entities.Project;
import com.example.demo.entities.Report;
import com.example.demo.entities.Task;
import com.example.demo.entities.WorkCalendar;
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

    final Address result = mapper.mapAddressDTO(addressDto);

    assertEquals("Street", result.getStreet());
    assertEquals("City", result.getCity());
    assertEquals("7034", result.getPostalCode());
  }

  @Test
  void mapCompanyDTO() {
    final CompanyDTO companyDTO = new CompanyDTO("Company Name");

    final Company result = mapper.mapCompanyDTO(companyDTO);

    assertEquals("Company Name", result.getName());
  }

  @Test
  void mapDepartmentDTO() {
    final Employee employee = new Employee(1L, "oumaima");
    final List<Employee> employees = List.of(employee);
    final DepartmentDTO departmentDTO = new DepartmentDTO("Info", employees);

    final Department result = mapper.mapDepartmentDTO(departmentDTO);

    assertEquals("Info", result.getName());
    assertEquals(employees.size(), result.getEmployees().size());
  }

  @Test
  void mapEmployeeDTO() {
    final EmployeeDTO employeeDTO =
        new EmployeeDTO("Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);

    final Employee result = mapper.mapEmployeeDTO(employeeDTO);

    assertEquals("Oumaima L", result.getName());
    assertEquals(Gender.FEMALE, result.getGender());
    assertEquals(ContractType.CDI, result.getContractType());
    assertEquals(1000, result.getSalary());
  }

  @Test
  void mapMeetingDTO() {
    final MeetingDTO meetingDTO = new MeetingDTO("meeting");

    final Meeting result = mapper.mapMeetingDTO(meetingDTO);

    assertEquals("meeting", result.getTitle());
  }

  @Test
  void mapProject() {
    final ProjectDTO projectDTO = new ProjectDTO("project");

    final Project result = mapper.mapProjectDTO(projectDTO);

    assertEquals("project", result.getName());
  }

  @Test
  void mapReport() {
    final ReportDTO reportDTO = new ReportDTO("report");

    final Report result = mapper.mapReport(reportDTO);

    assertEquals("report", result.getTitle());
  }

  @Test
  void mapTask() {
    final TaskDTO taskDTO =
        new TaskDTO("task", "description", Priority.HIGH, TaskStatus.IN_PROGRESS);

    final Task result = mapper.mapTask(taskDTO);

    assertEquals("task", result.getName());
    assertEquals("description", result.getDescription());
    assertEquals(TaskStatus.IN_PROGRESS, result.getTaskStatus());
    assertEquals(Priority.HIGH, result.getPriority());
  }

  @Test
  void mapWorkCalendar() {
    final WorkCalendarDTO workCalendarDTO = new WorkCalendarDTO("tag");

    final WorkCalendar result = mapper.mapWorkCalendar(workCalendarDTO);

    assertEquals("tag", result.getTag());
  }
}
