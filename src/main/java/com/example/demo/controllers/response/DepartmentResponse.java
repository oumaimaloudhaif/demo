package com.example.demo.controllers.response;

import com.example.demo.dto.DepartmentDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {
  List<DepartmentDTO> result;
}
