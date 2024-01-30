package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.FetchEmployeeResponse;
import com.example.demo.Dto.EmployeeDTO;
import com.example.demo.Entities.Address;
import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;

import java.time.LocalDate;
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

  /*  @BeforeEach
  void setUp() {
     employeeMapper=new EmployeeMapper();
  }*/

  @Test
  public void toFetchEmployeeResponseTest() {
    // Given
    Address address = new Address();
    address.setCity("address");
    EmployeeDTO employee1 = new EmployeeDTO("oumaima", 1200, LocalDate.now(),address,LocalDate.now(),Gender.FEMALE, ContractType.CDI);
    EmployeeDTO employee2 = new EmployeeDTO("Basssem", 5200,LocalDate.now(),address,LocalDate.now(), Gender.MALE,ContractType.CDI);
    List<EmployeeDTO> employees = List.of(employee1, employee2);

    // When
    FetchEmployeeResponse result = employeeMapper.toFetchEmployeeResponse(employees);

    // Then
    Assert.assertEquals(result.getResult().size(), 2);
  }
}
