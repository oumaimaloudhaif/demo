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
import com.example.demo.entities.Report;
import com.example.demo.entities.Task;
import com.example.demo.entities.Meeting;
import com.example.demo.entities.Project;
import com.example.demo.entities.WorkCalendar;

import org.springframework.stereotype.Component;

@Component
public class FromDOToDTO {

  public AddressDTO MapAddress(Address address) {
    AddressDTO addressDTO = new AddressDTO();
    addressDTO.setStreet(address.getStreet());
    addressDTO.setCity(address.getCity());
    addressDTO.setPostalCode(address.getPostalCode());

    return addressDTO;
  }

  public CompanyDTO MapCompany(Company company) {
    CompanyDTO companyDTO = new CompanyDTO();
    companyDTO.setName(company.getName());

    return companyDTO;
  }

  public DepartmentDTO MapDepartment(Department department) {
    DepartmentDTO departmentDTO = new DepartmentDTO();
    departmentDTO.setName(department.getName());
    departmentDTO.setEmployees(department.getEmployees());

    return departmentDTO;
  }

  public EmployeeDTO MapEmployee(Employee employee) {
    EmployeeDTO employeeDTO = new EmployeeDTO();
    employeeDTO.setGender(employee.getGender());
    employeeDTO.setAddress(employee.getAddress());
    employeeDTO.setContractType(employee.getContractType());
    employeeDTO.setSalary(employee.getSalary());
    employeeDTO.setJoiningDate(employee.getJoiningDate());
    employeeDTO.setName(employee.getName());
    employeeDTO.setDateOfBirth(employee.getJoiningDate());

    return employeeDTO;
  }

  public MeetingDTO MapMeeting(Meeting meeting) {
    MeetingDTO meetingDTO = new MeetingDTO();
    meetingDTO.setTitle(meeting.getTitle());
    meetingDTO.setStartTime(meeting.getStartTime());

    return meetingDTO;
  }

  public ProjectDTO MapProject(Project project) {
    ProjectDTO projectDTO = new ProjectDTO();
    projectDTO.setName(project.getName());
    projectDTO.setEmployees(project.getEmployees());

    return projectDTO;
  }

  public ReportDTO MapReport(Report report) {
    ReportDTO reportDTO = new ReportDTO();
    reportDTO.setTitle(report.getTitle());
    reportDTO.setContent(report.getContent());

    return reportDTO;
  }

  public TaskDTO MapTask(Task task) {
    TaskDTO taskDTO = new TaskDTO();
    taskDTO.setDescription(task.getDescription());
    taskDTO.setPriority(task.getPriority());
    taskDTO.setName(task.getName());
    taskDTO.setTaskStatus(task.getTaskStatus());

    return taskDTO;
  }

  public WorkCalendarDTO MapWorkCalendar(WorkCalendar workCalendar) {
    WorkCalendarDTO workCalanderDTO = new WorkCalendarDTO();
    workCalanderDTO.setStartTime(workCalendar.getStartTime());
    workCalanderDTO.setEndTime(workCalendar.getEndTime());

    return workCalanderDTO;
  }
}
