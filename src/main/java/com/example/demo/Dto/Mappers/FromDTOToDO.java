package com.example.demo.Dto.Mappers;

import com.example.demo.Dto.*;
import com.example.demo.Entities.*;
import org.springframework.stereotype.Component;

@Component
public class FromDTOToDO {

  public Address MapAddressDTO(AddressDTO adressDTO) {
    Address address = new Address();
    address.setCity(adressDTO.city());
    address.setPostalCode(adressDTO.postalCode());
    address.setStreet(adressDTO.street());
    return address;
  }

  public Company MapCompanyDTO(CompanyDTO companyDTO) {
    Company company = new Company();
    company.setName(companyDTO.name());
    return company;
  }

  public Department MapDepartmentDTO(DepartmentDTO departmentDTO) {
    Department department = new Department();
    department.setName(departmentDTO.name());
    department.setEmployees(departmentDTO.employees());
    return department;
  }

  public Employee MapEmployeeDTO(EmployeeDTO employeeDTO) {
    Employee employee = new Employee();
    employee.setName(employeeDTO.name());
    employee.setAddress(employeeDTO.address());
    employee.setSalary(employeeDTO.salary());
    employee.setGender(employeeDTO.gender());
    employee.setContractType(employeeDTO.contractType());
    employee.setDateOfBirth(employeeDTO.dateOfBirth());
    employee.setJoiningDate(employeeDTO.joiningDate());
    return employee;
  }

  public Meeting MapMeetingDTO(MeetingDTO meetingDTO) {
    Meeting meeting = new Meeting();
    meeting.setTitle(meetingDTO.title());
    meeting.setStartTime(meetingDTO.startTime());
    return meeting;
  }

  public Project MapProjectDTO(ProjectDTO projectDTO) {
    Project project = new Project();
    project.setName(projectDTO.name());
    project.setEmployees(projectDTO.employees());
    return project;
  }

  public Report MapReport(ReportDTO reportDTO) {
    Report report = new Report();
    report.setTitle(reportDTO.title());
    report.setContent(reportDTO.content());
    return report;
  }

  public Task MapTask(TaskDTO taskDTO) {
    Task task = new Task();
    task.setName(taskDTO.name());
    task.setDescription(taskDTO.description());
    task.setPriority(taskDTO.priority());
    task.setTaskStatus(taskDTO.taskStatus());
    return task;
  }
}
