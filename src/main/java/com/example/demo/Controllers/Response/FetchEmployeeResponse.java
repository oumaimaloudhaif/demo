package com.example.demo.Controllers.Response;

import com.example.demo.Dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FetchEmployeeResponse {
     List<EmployeeDTO> result;
}