package com.example.demo.Dto.Mappers;

import com.example.demo.Dto.*;
import com.example.demo.Entities.*;
import org.springframework.stereotype.Component;

@Component
public class FromDTOToDO {


    public Address MapAddressDTO(AdressDTO adressDTO){
        Address address=new Address();
        address.setCity(adressDTO.getCity());
        address.setPostalCode(adressDTO.getPostalCode());
        address.setStreet(adressDTO.getStreet());
        return address;
    }

    public Company MapCompanyDTO(CompanyDTO companyDTO){
        Company company=new Company();
        company.setName(companyDTO.getName());
        return company;
    }
    public Department MapDepartmentDTO(DepartmentDTO departmentDTO){
        Department department=new Department();
        department.setName(departmentDTO.getName());
        department.setEmployees(departmentDTO.getEmployees());
        return department;
    }
    public Employee MapEmployeeDTO(EmployeeDTO employeeDTO){
        Employee employee=new Employee();
        employee.setName(employeeDTO.getName());
        employee.setAddress(employeeDTO.getAddress());
        employee.setSalary(employeeDTO.getSalary());
        employee.setGender(employeeDTO.getGender());
        employee.setContractType(employeeDTO.getContractType());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setJoiningDate(employeeDTO.getJoiningDate());
        return employee;
    }

    public Meeting MapMeetingDTO(MeetingDTO meetingDTO){
        Meeting meeting=new Meeting();
        meeting.setTitle(meetingDTO.getTitle());
        meeting.setStartTime(meetingDTO.getStartTime());
        return meeting;
    }
    public Project MapProjectDTO(ProjectDTO projectDTO){
        Project project=new Project();
        project.setName(projectDTO.getName());
        project.setEmployees(projectDTO.getEmployees());
        return project;
    }
    public Report MapReport(ReportDTO reportDTO){
        Report report=new Report();
        report.setTitle(reportDTO.getTitle());
        report.setContent(reportDTO.getContent());
        return report;
    }
    public Task MapTask(TaskDTO taskDTO){
        Task task=new Task();
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());
        task.setTaskStatus(taskDTO.getTaskStatus());
        return task;
    }
}
