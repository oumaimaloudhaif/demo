package com.example.demo.controllers.request;

import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Employee Request */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeRequest {
  @Pattern(regexp = "[a-zA-Z]*", message = "THE KEYWORD MUST CONTAINS ONLY LETTERS")
  String keyword;
}
