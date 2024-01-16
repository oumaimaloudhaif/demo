package com.example.demo.Controllers.Request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeRequest {
  @NotNull
  @NotEmpty(message = "The keyword MUST NOT BE EMPTY")
  @Pattern(regexp = "[a-zA-Z]*", message = "The keyword must contains only letters")
  // @Email
  // @Past
  // @Size
  String keyword;
}
