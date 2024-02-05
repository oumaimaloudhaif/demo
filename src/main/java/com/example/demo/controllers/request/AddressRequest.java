package com.example.demo.controllers.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressRequest {
  @NotNull
  @NotEmpty(message = "ADDRESS NAME NOT BE EMPTY")
  private String keyword;
}
