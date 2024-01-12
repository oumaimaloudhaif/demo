package com.example.demo.Controllers;

import com.example.demo.Controllers.Mappers.CompanyMapper;
import com.example.demo.Controllers.Request.CompanyRequest;
import com.example.demo.Controllers.Response.CompanyResponse;
import com.example.demo.Entities. Company;
import com.example.demo.ServicesImpl. CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CompanyController {
    @Autowired
    private  CompanyServiceImpl  CompanyServiceImpl;
    @Autowired
    private  CompanyMapper  companyMapper;

    /**
     *
     * @return
     */
   /* @GetMapping("/companies")
    public  CompanyResponse getAllCompanies() {

        return  companyMapper.toCompanyResponse( CompanyServiceImpl.getAllCompanies());
    }*/
    @PostMapping("/companies")
    public  Company addCompany(@RequestBody @Valid  Company  Company) {
        return  CompanyServiceImpl.addCompany( Company);
    }
    /**
     *
     *
     *@return  Companies
     */
    @PutMapping("/companies")
    public  Company updateCompany(@RequestBody @Valid  Company  Company) {
        return  CompanyServiceImpl.updateCompany( Company);
    }
    /**
     *
     * @return  CompanyResponse
     */
   /* @GetMapping("/companies")
    public  CompanyResponse searchDepartment(@RequestParam(required = false) @Valid CompanyRequest companyRequest) {
        return  companyMapper.toCompanyResponse( CompanyServiceImpl.searchCompany(companyRequest.getKeyword()));
    }*/
    @GetMapping("/companies")
    public CompanyResponse getCompanies(@RequestParam(required = false) @Valid CompanyRequest companyRequest) {
        if (companyRequest != null && companyRequest.getKeyword() != null) {
            return companyMapper.toCompanyResponse(CompanyServiceImpl.searchCompany(companyRequest.getKeyword()));
        } else {
            return companyMapper.toCompanyResponse(CompanyServiceImpl.getAllCompanies());
        }
    }
}