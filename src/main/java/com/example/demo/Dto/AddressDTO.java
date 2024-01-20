package com.example.demo.Dto;

import lombok.*;

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
