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
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = DemoApplication.class)
public class FromDOToDTOTest {

  @Autowired FromDOToDTO mapper;

  @BeforeEach
  public void setUp() {}

  @Test
  public void testMapAddress() {
    final Address address = new Address("Street", "City", "7034");

    final AddressDTO result = mapper.mapAddress(address);

    assertEquals("Street", result.getStreet());
    assertEquals("City", result.getCity());
    assertEquals("7034", result.getPostalCode());
  }

  @Test
  public void testMapCompany() {
    final Company company = new Company("Company Name");

    final CompanyDTO result = mapper.mapCompany(company);

    assertEquals("Company Name", result.getName());
  }

  @Test
  public void testMapWorkCalendar() {
    final LocalDateTime startTime = LocalDateTime.now();
    final LocalDateTime endTime = LocalDateTime.now().minusHours(8);
    final WorkCalendar workCalendar = new WorkCalendar(startTime, endTime);

    final WorkCalendarDTO result = mapper.mapWorkCalendar(workCalendar);

    assertEquals(startTime, result.getStartTime());
    assertEquals(endTime, result.getEndTime());
  }

  @Test
  void mapDepartment() {
    final Employee employee = new Employee(1L, "oumaima");
    final List<Employee> employees = List.of(employee);
    final Department department = new Department("Info", employees);

    final DepartmentDTO result = mapper.mapDepartment(department);

    assertEquals("Info", result.getName());
    assertEquals(employees.size(), result.getEmployees().size());
  }

  @Test
  void mapEmployee() {
    final Employee employee = new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);

    final EmployeeDTO result = mapper.mapEmployee(employee);

    assertEquals("Oumaima L", result.getName());
    assertEquals(Gender.FEMALE, result.getGender());
    assertEquals(ContractType.CDI, result.getContractType());
    assertEquals(1000, result.getSalary());
  }

  @Test
  void mapMeeting() {
    final Meeting meeting = new Meeting("meeting");

    final MeetingDTO result = mapper.mapMeeting(meeting);

    assertEquals("meeting", result.getTitle());
  }

  @Test
  void mapProject() {
    final Project project = new Project("project");

    final ProjectDTO result = mapper.mapProject(project);

    assertEquals("project", result.getName());
  }

  @Test
  void mapReport() {
    final Report report = new Report("report");

    final ReportDTO result = mapper.mapReport(report);

    assertEquals("report", result.getTitle());
  }

  @Test
  void mapTask() {
    final Task task = new Task("task", "description", TaskStatus.IN_PROGRESS, Priority.HIGH);

    final TaskDTO result = mapper.mapTask(task);

    assertEquals("task", result.getName());
    assertEquals("description", result.getDescription());
    assertEquals(TaskStatus.IN_PROGRESS, result.getTaskStatus());
    assertEquals(Priority.HIGH, result.getPriority());
  }
}
