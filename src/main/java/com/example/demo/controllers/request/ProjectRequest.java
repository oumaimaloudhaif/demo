package com.example.demo.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Project Request */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectRequest {
  private String keyword;
}
