package com.example.demo.controllers.response;

import com.example.demo.dto.CompanyDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Company Response */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponse {
  List<CompanyDTO> result;
}
