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

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeServiceImpl.getAllEmployees();
    }

    /**
     *
     * @param keyword
     * @return
     */
    @GetMapping("/searchEmployees")
    public List<Employee> searchEmployees(@RequestParam(required = false) String keyword) {
        if(keyword==null){
           return  List.of();
        }
        return employeeServiceImpl.searchEmployees(keyword);
    }

}
