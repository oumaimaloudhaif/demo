package com.example.demo.Controllers;

import com.example.demo.ServicesImpl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired

    private DepartmentServiceImpl departmentServiceImpl;

}
