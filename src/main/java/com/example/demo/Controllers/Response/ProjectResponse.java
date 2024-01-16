package com.example.demo.Controllers.Response;

import com.example.demo.Dto.ProjectDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
  List<ProjectDTO> result;
}
