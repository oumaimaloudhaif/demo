package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.FetchEmployeeResponse;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.enums.ContractType;
import com.example.demo.enums.Gender;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeMapperTest {
  @Autowired private EmployeeMapper employeeMapper;
  @Test
  public void toFetchEmployeeResponseTest() {
    // Given
    EmployeeDTO employee1 = new EmployeeDTO("Oumaima", 1200, Gender.FEMALE, ContractType.CDI);
    EmployeeDTO employee2 = new EmployeeDTO("Leila", 5200, Gender.MALE, ContractType.CDI);
    List<EmployeeDTO> employees = List.of(employee1, employee2);

    // When
    FetchEmployeeResponse result = employeeMapper.toFetchEmployeeResponse(employees);

    // Then
    Assert.assertEquals(result.getResult().size(), 2);
  }
}
