package com.example.demo.dto.mappers;

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
import org.springframework.stereotype.Component;

@Component
public class FromDTOToDO {

  public Address mapAddressDTO(AddressDTO addressDTO) {
    Address address = new Address();
    address.setCity(addressDTO.getCity());
    address.setPostalCode(addressDTO.getPostalCode());
    address.setStreet(addressDTO.getStreet());

    return address;
  }

  public Company mapCompanyDTO(CompanyDTO companyDTO) {
    Company company = new Company();
    company.setName(companyDTO.getName());

    return company;
  }

  public Department mapDepartmentDTO(DepartmentDTO departmentDTO) {
    Department department = new Department();
    department.setName(departmentDTO.getName());
    department.setEmployees(departmentDTO.getEmployees());

    return department;
  }

  public Employee mapEmployeeDTO(EmployeeDTO employeeDTO) {
    Employee employee = new Employee();
    employee.setName(employeeDTO.getName());
    employee.setAddress(employeeDTO.getAddress());
    employee.setSalary(employeeDTO.getSalary());
    employee.setGender(employeeDTO.getGender());
    employee.setContractType(employeeDTO.getContractType());
    employee.setDateOfBirth(employeeDTO.getDateOfBirth());
    employee.setJoiningDate(employeeDTO.getJoiningDate());

    return employee;
  }

  public Meeting mapMeetingDTO(MeetingDTO meetingDTO) {
    Meeting meeting = new Meeting();
    meeting.setTitle(meetingDTO.getTitle());
    meeting.setStartTime(meetingDTO.getStartTime());

    return meeting;
  }

  public Project mapProjectDTO(ProjectDTO projectDTO) {
    Project project = new Project();
    project.setName(projectDTO.getName());
    project.setEmployees(projectDTO.getEmployees());

    return project;
  }

  public Report mapReport(ReportDTO reportDTO) {
    Report report = new Report();
    report.setTitle(reportDTO.getTitle());
    report.setContent(reportDTO.getContent());

    return report;
  }

  public Task mapTask(TaskDTO taskDTO) {
    Task task = new Task();
    task.setName(taskDTO.getName());
    task.setDescription(taskDTO.getDescription());
    task.setPriority(taskDTO.getPriority());
    task.setTaskStatus(taskDTO.getTaskStatus());

    return task;
  }

  public WorkCalendar mapWorkCalendar(WorkCalendarDTO workCalendarDTO) {
    WorkCalendar workCalendar = new WorkCalendar();
    workCalendar.setTag(workCalendarDTO.getTag());
    workCalendar.setStartTime(workCalendarDTO.getStartTime());
    workCalendar.setEndTime(workCalendarDTO.getEndTime());
    return workCalendar;
  }
}
