package com.example.demo.Controllers;

import com.example.demo.Entities.Address;
import com.example.demo.ServicesImpl.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {
    @Autowired
    private AddressServiceImpl addressServiceImpl;

    /**
     *
     * @return
     */
    @GetMapping("/addresses")
    public List<Address> getAllAddresses() {
        return addressServiceImpl.getAllAddresses();
    }

}
