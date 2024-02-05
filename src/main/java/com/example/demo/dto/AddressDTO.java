package com.example.demo.dto;

import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@With
public class AddressDTO {
  private String street;

  private String city;

  private String postalCode;
}
