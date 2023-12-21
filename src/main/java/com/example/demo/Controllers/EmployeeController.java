package com.example.demo.Controllers;

import com.example.demo.Controllers.Mappers.EmployeeMapper;
import com.example.demo.Controllers.Request.EmployeeRequest;
import com.example.demo.Controllers.Response.FetchEmployeeResponse;
import com.example.demo.Entities.Employee;
import com.example.demo.ServicesImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;
    @Autowired
    private EmployeeMapper employeeMapper;

    // @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeServiceImpl.getAllEmployees();
    }

    /**
     *
     * @param keyword
     * @return
     */
    @GetMapping("/employees")
    public List<Employee> searchEmployees(@RequestParam(required = false) String keyword) {
        if(keyword==null){
            return  List.of();
        }
        return employeeServiceImpl.searchEmployees(keyword);
    }
    /*
     * @param keyword
     * @return
     */
    @GetMapping(value="/fetch", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public FetchEmployeeResponse fetchEmployees(@RequestBody EmployeeRequest employeeRequest) {
        final String keyword=employeeRequest.getKeyword();

        if(keyword==null){
            // new FetchEmployeeResponse(List.of());
            return employeeMapper.toFetchEmployeeResponse(List.of());
            //return "Hello";
        }
        final  List<Employee> result=employeeServiceImpl.searchEmployees(keyword);
        // return new FetchEmployeeResponse(result);
        return  employeeMapper.toFetchEmployeeResponse(result);
        //return "salut";

    }
}
