package com.example.demo.Controllers.Response;

import com.example.demo.dto.TaskDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
  List<TaskDTO> result;
}
