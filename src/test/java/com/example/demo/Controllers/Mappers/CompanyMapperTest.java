package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.CompanyResponse;
import com.example.demo.dto.CompanyDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyMapperTest {
  @Autowired private CompanyMapper companyMapper;
  @Test
  public void toCompanyResponseTest() {
    // Given

    final CompanyDTO companyDTO = new CompanyDTO("company");
    final CompanyDTO companyDTO1 = new CompanyDTO("company");
    List<CompanyDTO> AddressDTOs = List.of(companyDTO, companyDTO1);

    // When
    CompanyResponse result = companyMapper.toCompanyResponse(AddressDTOs);

    // Then
    Assert.assertEquals(result.getResult().size(), 2);
  }
}
