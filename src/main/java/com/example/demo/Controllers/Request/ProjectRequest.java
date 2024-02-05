package com.example.demo.Controllers.Request;

import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectRequest {
  private String keyword;
}
