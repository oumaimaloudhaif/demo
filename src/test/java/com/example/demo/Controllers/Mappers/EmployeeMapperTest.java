package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.FetchEmployeeResponse;
import com.example.demo.Entities.Employee;
import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeMapperTest {
    @Autowired
    private EmployeeMapper employeeMapper;

    /*  @BeforeEach
    void setUp() {
       employeeMapper=new EmployeeMapper();
    }*/

   @Test
   public void toFetchEmployeeResponseTest() {
        //Given
       Employee employee1=new Employee(1L,"oumaima",1200, Gender.FEMALE, ContractType.CDI);
       Employee employee2=new Employee(1L,"Basssem",5200, Gender.MALE, ContractType.CDI);
       List<Employee> employees=List.of(employee1,employee2);

       //When
       FetchEmployeeResponse result=employeeMapper.toFetchEmployeeResponse(employees);

       //Then
       Assert.assertEquals(result.getResult().size(),2);
   }
}