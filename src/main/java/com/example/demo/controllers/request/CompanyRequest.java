package com.example.demo.controllers.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Company Request */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyRequest {
  @NotNull
  @NotEmpty(message = "COMPANY NAME NOT BE EMPTY")
  private String keyword;
}
