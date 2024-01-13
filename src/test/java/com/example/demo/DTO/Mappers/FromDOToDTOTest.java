package com.example.demo.DTO.Mappers;

import com.example.demo.DemoApplication;
import com.example.demo.Dto.*;
import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Entities.*;
import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;
import com.example.demo.Enums.Priority;
import com.example.demo.Enums.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DemoApplication.class)
public class FromDOToDTOTest {
    FromDOToDTO mapper = new FromDOToDTO();
    @BeforeEach
    public void setUp() {

    }
    @Test
    public void testMapAddress() {
        Address address = new Address("Street", "City", "7034");

        AdressDTO result = mapper.MapAdress(address);

        assertEquals("Street", result.getStreet());
        assertEquals("City", result.getCity());
        assertEquals("7034", result.getPostalCode());
    }

    @Test
    public void testMapCompany() {
        Company company = new Company("Company Name");

        CompanyDTO result = mapper.MapCompany(company);

        assertEquals("Company Name", result.getName());
    }

    @Test
    public void testMapWorkCalander() {
        LocalDateTime startTime=LocalDateTime.now();
        LocalDateTime endTime=LocalDateTime.now().minusHours(8);
        WorkCalander workCalander = new WorkCalander(startTime, endTime);

        WorkCalanderDTO result = mapper.MapWorkCalander(workCalander);

        assertEquals(startTime, result.getStartTime());
        assertEquals(endTime, result.getEndTime());
    }

    @Test
    void mapDepartment() {
        Employee employee=new Employee(1L,"oumaima");
        List<Employee> employees=List.of(employee);
        Department department = new Department("Informatique", employees);

        DepartmentDTO result = mapper.MapDepartment(department);

        assertEquals("Informatique", result.getName());
        assertEquals(employees.size(), result.getEmployees().size());
    }

    @Test
    void mapEmployee() {
        Employee employee=new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);

        EmployeeDTO result = mapper.MapEmployee(employee);

        assertEquals("Oumaima L", result.getName());
        assertEquals(Gender.FEMALE, result.getGender());
        assertEquals(ContractType.CDI, result.getContractType());
        assertEquals( 1000, result.getSalary());
    }

    @Test
    void mapMeeting() {
        Meeting meeting=new Meeting("meeting");

        MeetingDTO result = mapper.MapMeeting(meeting);

        assertEquals("meeting", result.getTitle());
    }

    @Test
    void mapProject() {
        Project project=new Project("project");

        ProjectDTO result = mapper.MapProject(project);

        assertEquals("project", result.getName());
    }

    @Test
    void mapReport() {
        Report report=new Report("report");

        ReportDTO result = mapper.MapReport(report);

        assertEquals("report", result.getTitle());
    }

    @Test
    void mapTask() {
        Task task=new Task("task","description", TaskStatus.IN_PROGRESS, Priority.HIGH);

        TaskDTO result = mapper.MapTask(task);

        assertEquals("task", result.getName());
        assertEquals("description", result.getDescription());
        assertEquals(TaskStatus.IN_PROGRESS, result.getTaskStatus());
        assertEquals(Priority.HIGH, result.getPriority());
    }

}