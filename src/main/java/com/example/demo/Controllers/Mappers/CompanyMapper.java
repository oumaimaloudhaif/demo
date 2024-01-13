package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.CompanyResponse;
import com.example.demo.Dto.CompanyDTO;
import com.example.demo.Entities.Company;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyMapper {
    public CompanyResponse toCompanyResponse(List<CompanyDTO> companies) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setResult(companies);
        return companyResponse;
    }
}
