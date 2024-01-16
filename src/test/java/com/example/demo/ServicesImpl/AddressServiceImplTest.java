package com.example.demo.ServicesImpl;

import com.example.demo.DemoApplication;
import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Dto.AddressDTO;
import com.example.demo.Entities.Address;
import com.example.demo.Repository.AddressRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DemoApplication.class)
@AutoConfigureMockMvc
public class AddressServiceImplTest {
    @Mock
    private AddressRepository addressRepository;
    @InjectMocks
    private AddressServiceImpl addressService;
    @Mock
    private FromDOToDTO fromDOToDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllAddress() {
        List<Address> mockedAddress = Arrays.asList(
                new Address("street","city","Address1"),
                new Address("street1","city1","Address1")
        );
        when(addressRepository.findAll()).thenReturn(mockedAddress);
        List<AddressDTO> address = addressService.getAllAddresses();
        assertEquals(mockedAddress.size(), address.size());
    }
    @Test
    public void testSearchAddresss() {
        String keyword = "Oumaima";
        List<Address> mockedAddress = Arrays.asList(
                new Address("street","city","Address1"),
                new Address("street1","city1","Address1")
        );
        when(addressRepository.findByCity(keyword)).thenReturn(mockedAddress);
        List<AddressDTO> address = addressService.searchAddress(keyword);
        assertEquals(mockedAddress.size(), address.size());
    }
    @Test
    public void testAddAddress() {
        Address inputAddress = new Address("street","city","Address1");
        Address savedAddress = new Address("street","city","Address1");
        AddressDTO expectedAddressDTO = new AddressDTO("street","city","Address1");

        when(addressRepository.save(inputAddress)).thenReturn(savedAddress);
        when(fromDOToDTO.MapAdress(savedAddress)).thenReturn(expectedAddressDTO);

        AddressDTO resultAddressDTO = addressService.addAddress(inputAddress);

        assertEquals(expectedAddressDTO, resultAddressDTO);
    }
    @Test
   public void testUpdateAddress() {
        Address inputAddress = new Address("street","city","Address1");
        Address updatedAddress = new Address("street","city","Address1");
        AddressDTO expectedAddressDTO = new AddressDTO("street","city","Address1");

        when(addressRepository.save(inputAddress)).thenReturn(updatedAddress);
        when(fromDOToDTO.MapAdress(updatedAddress)).thenReturn(expectedAddressDTO);

        AddressDTO resultAddressDTO = addressService.addAddress(inputAddress);

        assertEquals(expectedAddressDTO, resultAddressDTO);
    }
}