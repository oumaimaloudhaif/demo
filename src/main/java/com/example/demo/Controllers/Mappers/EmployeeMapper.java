package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.FetchEmployeeResponse;
import com.example.demo.Dto.EmployeeDTO;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
  public FetchEmployeeResponse toFetchEmployeeResponse(List<EmployeeDTO> employees) {
    FetchEmployeeResponse fetchEmployeeResponse = new FetchEmployeeResponse();
    fetchEmployeeResponse.setResult(employees);
    return fetchEmployeeResponse;
  }
}
