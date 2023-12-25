package com.example.demo.Controllers;

import com.example.demo.Entities.Employee;
import com.example.demo.Services.EmployeeService;
import com.example.demo.ServicesImpl.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EmployeeControllerTest extends AbstractTest{
    // on utilise Mock si on a pas de bean deja cree
    //Donc on utilise tjrs MockBean
    @MockBean
    EmployeeServiceImpl employeeServiceImpl;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }
    @Test
    public void getAllEmployeesTestWhenEmployeeExist() throws Exception {
        //given
        String uri = "/employees";
        Employee employee = new Employee();
        employee.setName("ouma");
        employee.setSalary(5000);
        Employee employee2 = new Employee();
        employee2.setName("ouma");
        employee2.setSalary(5000);
        List<Employee> listOfEmployees=List.of(employee,employee2);

        //when
        when(employeeServiceImpl.getAllEmployees()).thenReturn(listOfEmployees);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        //then
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Employee[] employees = super.mapFromJson(content, Employee[].class);
        assertTrue(employees.length > 0);

    }

    @Test
    public void getAllEmployeesTestWhenNoEmployeeExist() throws Exception {
        //given
        String uri = "/employees";
        List<Employee> listOfEmployees=List.of();

        //when
        when(employeeServiceImpl.getAllEmployees()).thenReturn(listOfEmployees);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        //then
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Employee[] employees = super.mapFromJson(content, Employee[].class);
        assertEquals(0, employees.length);

    }

    @Test
    public void getAllEmployeesTestWrongPath() throws Exception {
        //given
        String uri = "/emloyees";

        //when
        mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    public void getAllEmployeesTestThenThrowException() throws Exception {
        //given
        String uri = "/employees";

        //when
        when(employeeServiceImpl.getAllEmployees()).thenThrow(new RuntimeException());
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(),response.getStatus());
    }
    @Test
    public void searchEmployeeTestWhenKeyWordNotNull() throws Exception {
        //given
        String uri = "/searchEmployee";

        String keyword="o";
        Employee employee = new Employee();
        employee.setName("ouma");
        employee.setSalary(5000);
        Employee employee2 = new Employee();
        employee2.setName("ouma1");
        employee2.setSalary(6000);
        List<Employee> listOfEmployees=List.of(employee,employee2);
        //when
        when(employeeServiceImpl.searchEmployees(keyword)).thenReturn(listOfEmployees);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        //then
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Employee[] employees = super.mapFromJson(content, Employee[].class);
        assertEquals(2, employees.length);

    }



    @Test
    public void addEmployeeTest() throws Exception {

        //given
        String uri = "/addEmployee";
        Employee employee = new Employee();
        employee.setName("ouma");
        employee.setSalary(5000);
        String inputJson = super.mapToJson(employee);

        //when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        //then
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Employee added successfully");
    }
    @Test
    public void updateProduct() throws Exception {
        //given
        String uri = "/updateEmployee";
        Employee employee = new Employee();
        employee.setEmployee_id(1L);
        employee.setName("ouma1");
        employee.setSalary(5000);
        String inputJson = super.mapToJson(employee);

        //when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        //then
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Employee is updated successfully");
    }
    @Test
    public void deleteProduct() throws Exception {
        //given
        String uri = "/deleteEmployee/29";

        //when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        ///then
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Employee is deleted successfully");
    }
}