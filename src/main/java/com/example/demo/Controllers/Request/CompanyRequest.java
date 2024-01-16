package com.example.demo.Controllers.Request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.*;

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
