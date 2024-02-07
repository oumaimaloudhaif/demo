package com.example.demo.controllers.mappers;

import com.example.demo.controllers.response.FetchEmployeeResponse;
import com.example.demo.dto.EmployeeDTO;
import java.util.List;
import org.springframework.stereotype.Component;

/** Employee Mapper */
@Component
public class EmployeeMapper {
  public FetchEmployeeResponse toFetchEmployeeResponse(List<EmployeeDTO> employees) {
    FetchEmployeeResponse fetchEmployeeResponse = new FetchEmployeeResponse();
    fetchEmployeeResponse.setResult(employees);

    return fetchEmployeeResponse;
  }
}
