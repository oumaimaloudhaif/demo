package com.example.demo.Controllers;

import com.example.demo.Entities.Employee;
import com.example.demo.ServicesImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeServiceImpl.getAllEmployees();
    }

    /**
     *
     * @param keyword
     * @return
     */
    @GetMapping("/employees")
    public List<Employee> searchEmployees(@RequestParam String keyword) {
        return employeeServiceImpl.searchEmployees(keyword);
    }

}
