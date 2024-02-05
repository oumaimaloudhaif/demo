package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.DepartmentResponse;
import com.example.demo.dto.DepartmentDTO;
import java.util.List;
import org.springframework.stereotype.Component;

/*** Department Mapper ***/
@Component
public class DepartmentMapper {
  public DepartmentResponse toDepartmentResponse(List<DepartmentDTO> departments) {
    DepartmentResponse departmentResponse = new DepartmentResponse();
    departmentResponse.setResult(departments);

    return departmentResponse;
  }
}
