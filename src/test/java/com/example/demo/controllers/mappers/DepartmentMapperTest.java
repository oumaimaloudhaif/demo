package com.example.demo.controllers.mappers;

import com.example.demo.controllers.response.DepartmentResponse;
import com.example.demo.dto.DepartmentDTO;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentMapperTest {
  @Autowired private DepartmentMapper departmentMapper;

  @Test
  public void toDepartmentResponseTest() {
    // Given

    final DepartmentDTO departmentDTO = new DepartmentDTO("department");
    final DepartmentDTO departmentDTO1 = new DepartmentDTO("department");
    List<DepartmentDTO> DepartmentDTOs = List.of(departmentDTO, departmentDTO1);

    // When
    DepartmentResponse result = departmentMapper.toDepartmentResponse(DepartmentDTOs);

    // Then
    Assert.assertEquals(result.getResult().size(), 2);
  }
}
