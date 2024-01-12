package com.example.demo.Dto.Mappers;

import com.example.demo.Dto.*;
import com.example.demo.Entities.*;
import org.springframework.stereotype.Component;

@Component
public class FromDOToDTO {

    public AdressDTO MapAdress(Address address){
        AdressDTO adressDTO=new AdressDTO();
        adressDTO.setStreet(address.getStreet());
        adressDTO.setCity(address.getCity());
        adressDTO.setPostalCode(address.getPostalCode());
        return adressDTO;
    }

    public CompanyDTO MapCompany(Company company){
        CompanyDTO companyDTO=new CompanyDTO();
        companyDTO.setName(company.getName());
        return companyDTO;
    }

    public DepartmentDTO MapDepartment(Department department){
        DepartmentDTO departmentDTO=new DepartmentDTO();
        departmentDTO.setName(department.getName());
        departmentDTO.setEmployees(department.getEmployees());
        return departmentDTO;
    }
    public EmployeeDTO MapEmployee(Employee employee){
        EmployeeDTO employeeDTO=new EmployeeDTO();
        employeeDTO.setGender(employee.getGender());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setContractType(employee.getContractType());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setJoiningDate(employee.getJoiningDate());
        employeeDTO.setName(employee.getName());
        employeeDTO.setDateOfBirth(employee.getJoiningDate());
        return  employeeDTO;
    }
    public MeetingDTO MapMeeting(Meeting meeting){
        MeetingDTO meetingDTO=new MeetingDTO();
        meetingDTO.setTitle(meeting.getTitle());
        meetingDTO.setStartTime(meeting.getStartTime());
        return meetingDTO;
    }

    public ProjectDTO MapProject(Project project){
        ProjectDTO projectDTO=new ProjectDTO();
        projectDTO.setName(project.getName());
        projectDTO.setEmployees(project.getEmployees());
        return projectDTO;
    }

    public  ReportDTO  MapReport(Report report){
        ReportDTO reportDTO=new ReportDTO();
        reportDTO.setTitle(report.getTitle());
        reportDTO.setContent(report.getContent());
        return reportDTO;
    }

    public  TaskDTO MapTask(Task task){
        TaskDTO taskDTO=new TaskDTO();
        taskDTO.setDescription(task.getDescription());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setName(task.getName());
        taskDTO.setTaskStatus(task.getTaskStatus());
        return taskDTO;
    }

    public WorkCalanderDTO MapWorkCalander(WorkCalander workCalander){
        WorkCalanderDTO workCalanderDTO=new WorkCalanderDTO();
        workCalanderDTO.setStartTime(workCalander.getStartTime());
        workCalanderDTO.setEndTime(workCalander.getEndTime());
        return workCalanderDTO;

    }
}
