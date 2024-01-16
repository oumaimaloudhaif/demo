package com.example.demo.Controllers.Response;

import com.example.demo.Dto.ReportDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
  List<ReportDTO> result;
}
