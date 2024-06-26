package com.example.demo.controllers.response;

import com.example.demo.dto.ReportDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Report Response */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
  List<ReportDTO> result;
}
