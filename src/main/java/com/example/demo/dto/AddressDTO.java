package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

/** Address DTO */
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
