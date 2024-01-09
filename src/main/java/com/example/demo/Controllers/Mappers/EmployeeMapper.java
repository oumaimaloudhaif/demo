package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.FetchEmployeeResponse;
import com.example.demo.Entities.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {
    public FetchEmployeeResponse toFetchEmployeeResponse(List<Employee> employees){
        FetchEmployeeResponse fetchEmployeeResponse=new FetchEmployeeResponse();
        fetchEmployeeResponse.setResult(employees);
        return fetchEmployeeResponse;
    }
}
