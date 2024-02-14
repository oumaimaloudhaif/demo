package com.example.demo.controllers.response;

import com.example.demo.dto.EmployeeDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Fetch Employee Response */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FetchEmployeeResponse {
  List<EmployeeDTO> result;
}
