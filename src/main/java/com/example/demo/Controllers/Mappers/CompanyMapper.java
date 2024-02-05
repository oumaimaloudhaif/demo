package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.CompanyResponse;
import com.example.demo.dto.CompanyDTO;
import java.util.List;
import org.springframework.stereotype.Component;

/*** Company Mapper ***/
@Component
public class CompanyMapper {
  public CompanyResponse toCompanyResponse(List<CompanyDTO> companies) {
    CompanyResponse companyResponse = new CompanyResponse();
    companyResponse.setResult(companies);

    return companyResponse;
  }
}
