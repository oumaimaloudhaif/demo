package com.example.demo.Controllers;

import com.example.demo.Controllers.Mappers.AddressMapper;
import com.example.demo.Controllers.Request.DepartmentRequest;
import com.example.demo.Controllers.Response.AddressResponse;
import com.example.demo.Entities.Address;
import com.example.demo.ServicesImpl.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AddressController {
    @Autowired
    private AddressServiceImpl addressServiceImpl;
    @Autowired
    private AddressMapper addressMapper;

    /**
     *
     * @return
     */
    @GetMapping("/addresses")
    public AddressResponse getAllAddresses() {

        return addressMapper.toAddressResponse(addressServiceImpl.getAllAddresses());
    }
    @PostMapping("/addresses")
    public Address addAddress(@RequestBody @Valid Address address) {
        return addressServiceImpl.addAddress(address);
    }
    /**
     *
     *
     *@return addresses
     */
    @PutMapping("/addresses")
    public Address updateAddress(@RequestBody @Valid Address address) {
        return addressServiceImpl.updateAddress(address);
    }
    /**
     *
     * @return AddressResponse
     */
    @GetMapping("/addresses")
    public AddressResponse searchDepartment(@RequestParam(required = false) @Valid DepartmentRequest departmentRequest) {
        return addressMapper.toAddressResponse(addressServiceImpl.searchAddress(departmentRequest.getKeyword()));
    }
}