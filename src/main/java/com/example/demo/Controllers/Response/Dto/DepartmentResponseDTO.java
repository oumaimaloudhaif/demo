package com.example.demo.Controllers.Response.Dto;

import com.example.demo.Dto.DepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponseDTO {
    List<DepartmentDTO> result;
}