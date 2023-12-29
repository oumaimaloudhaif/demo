package com.example.demo.Controllers;

import com.example.demo.Controllers.Mappers.EmployeeMapper;
import com.example.demo.Controllers.Request.EmployeeRequest;
import com.example.demo.Controllers.Response.FetchEmployeeResponse;
import com.example.demo.Entities.Employee;
import com.example.demo.ServicesImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;
    @Autowired
    private EmployeeMapper employeeMapper;
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeServiceImpl.getAllEmployees();
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody  Employee employee) {
        return employeeServiceImpl.addEmployee(employee);
    }
    @PutMapping("/updateEmployee")
    public String updateEmployee(@RequestBody Employee employee) {
        return employeeServiceImpl.updateEmployee(employee);
    }
    @DeleteMapping("/deleteEmployee/{employeeId}")
    public String DeleteEmployee(@PathVariable  Long employeeId) {
        return employeeServiceImpl.deleteEmployee(employeeId);
    }
    /**
     *
     * @param keyword
     * @return
     */
    @GetMapping("/searchEmployee")
    public List<Employee> searchEmployees(@RequestParam(required = false) @Valid @NotEmpty @NotNull String keyword) {
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
    public FetchEmployeeResponse fetchEmployees(@RequestBody @Valid EmployeeRequest employeeRequest) {
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
