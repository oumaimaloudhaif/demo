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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
    @MockBean
    private AddressRepository addressRepository;
    @Autowired
    private AddressServiceImpl addressService;
    @Mock
    private FromDOToDTO fromDOToDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllAddress() {
        // Given
        List<Address> mockedAddress = Arrays.asList(
                new Address("street","city","Address1"),
                new Address("street1","city1","Address1")
        );
        // When
        when(addressRepository.findAll()).thenReturn(mockedAddress);
        List<AddressDTO> address = addressService.getAllAddresses();
        // Then
        assertEquals(mockedAddress.size(), address.size());
    }
    @Test
    public void testSearchAddress() {
        // Given
        final String keyword = "Oumaima";
        List<Address> mockedAddress = Arrays.asList(
                new Address("street","city","Address1"),
                new Address("street1","city1","Address1")
        );
        // When
        when(addressRepository.findByCity(keyword)).thenReturn(mockedAddress);
        List<AddressDTO> address = addressService.searchAddress(keyword);
        // Then
        assertEquals(mockedAddress.size(), address.size());
    }
    @Test
    public void testAddAddress() {
        // Given
        final Address inputAddress = new Address("street","city","Address1");
        final Address savedAddress = new Address("street","city","Address1");
        AddressDTO expectedAddressDTO = new AddressDTO("street","city","Address1");
// When
        when(addressRepository.save(inputAddress)).thenReturn(savedAddress);
        when(fromDOToDTO.MapAdress(savedAddress)).thenReturn(expectedAddressDTO);
        AddressDTO resultAddressDTO = addressService.addAddress(inputAddress);

        // Then
        assertEquals(expectedAddressDTO, resultAddressDTO);
    }
    @Test
   public void testUpdateAddress() {
        // Given
        Address inputAddress = new Address("street","city","Address1");
        Address updatedAddress = new Address("street","city","Address1");
        AddressDTO expectedAddressDTO = new AddressDTO("street","city","Address1");

        // When
        when(addressRepository.save(inputAddress)).thenReturn(updatedAddress);
        when(fromDOToDTO.MapAdress(updatedAddress)).thenReturn(expectedAddressDTO);
        AddressDTO resultAddressDTO = addressService.addAddress(inputAddress);

        // Then
        assertEquals(expectedAddressDTO, resultAddressDTO);
    }
}