package com.example.demo.Controllers.Response;

import com.example.demo.Entities.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    List<Project> result;
}