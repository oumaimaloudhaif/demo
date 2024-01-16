package com.example.demo.Controllers.Response;

import com.example.demo.Dto.AddressDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
  List<AddressDTO> result;
}
