package com.example.demo.DTO.Mappers;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.DemoApplication;
import com.example.demo.Dto.*;
import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Entities.*;
import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;
import com.example.demo.Enums.Priority;
import com.example.demo.Enums.TaskStatus;
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

    final AddressDTO result = mapper.MapAdress(address);

    assertEquals("Street", result.street());
    assertEquals("City", result.city());
    assertEquals("7034", result.postalCode());
  }

  @Test
  public void testMapCompany() {
    final Company company = new Company("Company Name");

    final CompanyDTO result = mapper.MapCompany(company);

    assertEquals("Company Name", result.name());
  }

  @Test
  public void testMapWorkCalendar() {
    final LocalDateTime startTime = LocalDateTime.now();
    final LocalDateTime endTime = LocalDateTime.now().minusHours(8);
    final WorkCalendar workCalendar = new WorkCalendar(startTime, endTime);

    final WorkCalanderDTO result = mapper.MapWorkCalander(workCalendar);

    assertEquals(startTime, result.startTime());
    assertEquals(endTime, result.endTime());
  }

  @Test
  void mapDepartment() {
    final Employee employee = new Employee(1L, "oumaima");
    final List<Employee> employees = List.of(employee);
    final Department department = new Department("Info", employees);

    final DepartmentDTO result = mapper.MapDepartment(department);

    assertEquals("Info", result.name());
    assertEquals(employees.size(), result.employees().size());
  }

  @Test
  void mapEmployee() {
    final Employee employee = new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);

    final EmployeeDTO result = mapper.MapEmployee(employee);

    assertEquals("Oumaima L", result.name());
    assertEquals(Gender.FEMALE, result.gender());
    assertEquals(ContractType.CDI, result.contractType());
    assertEquals(1000, result.salary());
  }

  @Test
  void mapMeeting() {
    final Meeting meeting = new Meeting("meeting");

    final MeetingDTO result = mapper.MapMeeting(meeting);

    assertEquals("meeting", result.title());
  }

  @Test
  void mapProject() {
    final Project project = new Project("project");

    final ProjectDTO result = mapper.MapProject(project);

    assertEquals("project", result.name());
  }

  @Test
  void mapReport() {
    final Report report = new Report("report");

    final ReportDTO result = mapper.MapReport(report);

    assertEquals("report", result.title());
  }

  @Test
  void mapTask() {
    final Task task = new Task("task", "description", TaskStatus.IN_PROGRESS, Priority.HIGH);

    final TaskDTO result = mapper.MapTask(task);

    assertEquals("task", result.name());
    assertEquals("description", result.description());
    assertEquals(TaskStatus.IN_PROGRESS, result.taskStatus());
    assertEquals(Priority.HIGH, result.priority());
  }
}
