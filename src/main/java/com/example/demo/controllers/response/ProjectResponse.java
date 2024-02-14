package com.example.demo.controllers.response;

import com.example.demo.dto.ProjectDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Project Response */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
  List<ProjectDTO> result;
}
