package com.example.demo.ServicesImpl;

import com.example.demo.Dto.CompanyDTO;
import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Entities.Company;
import com.example.demo.Entities.Department;
import com.example.demo.Entities.Employee;
import com.example.demo.Repository.CompanyRepository;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Company Service Implementation
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private FromDOToDTO fromDOToDTO;


    public boolean isSkillDiverse(String skill) {
        List<Employee> employees = employeeRepository.findAll();
        long countEmployeesWithSkill = employees.stream()
                .filter(employee -> employee.getSkills().contains(skill))
                .count();

        return countEmployeesWithSkill > 1;
    }
    public Department getMostOccupiedDepartment() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .max(Comparator.comparingInt(department -> department.getEmployees().size()))
                .orElse(null);
    }
    @Override
    public  List<CompanyDTO> searchCompany(String keyword) {
        List<Company> companies=companyRepository.findByName(keyword);
        List<CompanyDTO> companyDTOS=new ArrayList<>();
        companies.forEach(company -> {
            CompanyDTO companyDTO=fromDOToDTO.MapCompany(company);
            companyDTOS.add(companyDTO);
        });
        return companyDTOS;
    }
    @Override
    public  Company addCompany(Company company) {
        return companyRepository.save(company);
    }
    @Override
    public  Company updateCompany(Company company) {
        return companyRepository.save(company);
    }
    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<Company> companies=companyRepository.findAll();
        List<CompanyDTO> companyDTOS=new ArrayList<>();
        companies.forEach(company -> {
            CompanyDTO companyDTO=fromDOToDTO.MapCompany(company);
            companyDTOS.add(companyDTO);
        });
        return companyDTOS;
    }

}
