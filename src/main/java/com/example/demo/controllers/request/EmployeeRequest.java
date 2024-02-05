package com.example.demo.controllers.request;

import javax.validation.constraints.Pattern;
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
public class EmployeeRequest {
  @Pattern(regexp = "[a-zA-Z]*", message = "The keyword must contains only letters")
  String keyword;
}
