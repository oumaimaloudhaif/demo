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
public class FromDOToDTO {

  public AddressDTO mapAddress(Address address) {
    AddressDTO addressDTO = new AddressDTO();
    addressDTO.setStreet(address.getStreet());
    addressDTO.setCity(address.getCity());
    addressDTO.setPostalCode(address.getPostalCode());

    return addressDTO;
  }

  public CompanyDTO mapCompany(Company company) {
    CompanyDTO companyDTO = new CompanyDTO();
    companyDTO.setName(company.getName());

    return companyDTO;
  }

  public DepartmentDTO mapDepartment(Department department) {
    DepartmentDTO departmentDTO = new DepartmentDTO();
    departmentDTO.setName(department.getName());
    departmentDTO.setEmployees(department.getEmployees());

    return departmentDTO;
  }

  public EmployeeDTO mapEmployee(Employee employee) {
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

  public MeetingDTO mapMeeting(Meeting meeting) {
    MeetingDTO meetingDTO = new MeetingDTO();
    meetingDTO.setTitle(meeting.getTitle());
    meetingDTO.setStartTime(meeting.getStartTime());

    return meetingDTO;
  }

  public ProjectDTO mapProject(Project project) {
    ProjectDTO projectDTO = new ProjectDTO();
    projectDTO.setName(project.getName());
    projectDTO.setEmployees(project.getEmployees());

    return projectDTO;
  }

  public ReportDTO mapReport(Report report) {
    ReportDTO reportDTO = new ReportDTO();
    reportDTO.setTitle(report.getTitle());
    reportDTO.setContent(report.getContent());

    return reportDTO;
  }

  public TaskDTO mapTask(Task task) {
    TaskDTO taskDTO = new TaskDTO();
    taskDTO.setDescription(task.getDescription());
    taskDTO.setPriority(task.getPriority());
    taskDTO.setName(task.getName());
    taskDTO.setTaskStatus(task.getTaskStatus());

    return taskDTO;
  }

  public WorkCalendarDTO mapWorkCalendar(WorkCalendar workCalendar) {
    WorkCalendarDTO workCalendarDTO = new WorkCalendarDTO();
    workCalendarDTO.setStartTime(workCalendar.getStartTime());
    workCalendarDTO.setEndTime(workCalendar.getEndTime());

    return workCalendarDTO;
  }
}
