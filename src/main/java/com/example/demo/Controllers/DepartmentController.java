package com.example.demo.Controllers;

import com.example.demo.Controllers.Mappers.DepartmentMapper;
import com.example.demo.Controllers.Request.DepartmentRequest;
import com.example.demo.Controllers.Response.DepartmentResponse;
import com.example.demo.Dto.DepartmentDTO;
import com.example.demo.Entities.Department;
import com.example.demo.ServicesImpl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
;import javax.validation.Valid;

@Validated
@RestController
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;
    @Autowired
    private DepartmentMapper departmentMapper;

   /* @GetMapping("/departments")
    public DepartmentResponse getAllDepartments() {

        return departmentMapper.toDepartmentResponse(departmentServiceImpl.getAllDepartments());
    }*/
    @PostMapping("/departments")
    public DepartmentDTO addDepartment(@RequestBody @Valid Department department) {
        return departmentServiceImpl.addDepartment(department);
    }
    /**
     *
     *
     *@return Department
     */
    @PutMapping("/departments")
    public DepartmentDTO updateDepartment(@RequestBody @Valid Department department) {
        return departmentServiceImpl.updateDepartment(department);
    }
    /**
     *
     * @return DepartmentResponse
     */
    /*@GetMapping("/departments")
    public DepartmentResponse searchDepartment(@RequestParam(required = false) @Valid DepartmentRequest departmentRequest) {
        return departmentMapper.toDepartmentResponse(departmentServiceImpl.searchDepartment(departmentRequest.getKeyword()));
    }*/
    @GetMapping("/departments")
    public DepartmentResponse getDepartments(@RequestParam(required = false) @Valid DepartmentRequest departmentRequest) {
        if (departmentRequest != null && departmentRequest.getKeyword() != null) {
            return departmentMapper.toDepartmentResponse(departmentServiceImpl.searchDepartment(departmentRequest.getKeyword()));
        } else {
            return departmentMapper.toDepartmentResponse(departmentServiceImpl.getAllDepartments());
        }
    }
}
