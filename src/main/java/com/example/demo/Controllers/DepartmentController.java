package com.example.demo.Controllers;

import com.example.demo.Entities.Department;
import com.example.demo.ServicesImpl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentServiceImpl.getAllDepartments();
    }

}
