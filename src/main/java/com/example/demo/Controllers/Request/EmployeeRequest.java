package com.example.demo.Controllers.Request;

import javax.validation.constraints.Pattern;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeRequest {
  @Pattern(regexp = "[a-zA-Z]*", message = "The keyword must contains only letters")
  // @Email
  // @Past
  // @Size
  String keyword;
}
