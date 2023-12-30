package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.DepartmentResponse;
import com.example.demo.Entities.Department;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DepartmentMapper {
    public DepartmentResponse toDepartmentResponse(List<Department> departments) {
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setResult(departments);
        return departmentResponse;
    }
}
