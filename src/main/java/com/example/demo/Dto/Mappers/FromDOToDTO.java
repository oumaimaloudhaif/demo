package com.example.demo.Dto.Mappers;

import com.example.demo.Dto.*;
import com.example.demo.Entities.*;
import org.springframework.stereotype.Component;

@Component
public class FromDOToDTO {

  public AddressDTO MapAdress(Address address) {
      return new AddressDTO(address.getStreet(),address.getCity(),address.getPostalCode());
  }

  public CompanyDTO MapCompany(Company company) {
      return new CompanyDTO(company.getName());
  }

  public DepartmentDTO MapDepartment(Department department) {
      return new DepartmentDTO(department.getName(),department.getEmployees());
  }

  public EmployeeDTO MapEmployee(Employee employee) {
      return new EmployeeDTO(employee.getName(),employee.getSalary(),employee.getDateOfBirth(),employee.getAddress(),employee.getJoiningDate(),employee.getGender(),employee.getContractType());
  }

  public MeetingDTO MapMeeting(Meeting meeting) {
      return new MeetingDTO(meeting.getTitle(),meeting.getStartTime());
  }

  public ProjectDTO MapProject(Project project) {
      return new ProjectDTO(project.getName(),project.getEmployees());
  }

  public ReportDTO MapReport(Report report) {
      return new ReportDTO(report.getTitle(),report.getContent());
  }

  public TaskDTO MapTask(Task task) {
      return new TaskDTO(task.getDescription(),task.getName(),task.getPriority(),task.getTaskStatus());
  }

  public WorkCalanderDTO MapWorkCalander(WorkCalendar workCalander) {
      return new WorkCalanderDTO(workCalander.getStartTime(),workCalander.getEndTime());
  }
}
