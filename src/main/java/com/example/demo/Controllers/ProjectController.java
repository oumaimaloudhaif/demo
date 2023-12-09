package com.example.demo.Controllers;

import com.example.demo.Entities.Project;
import com.example.demo.ServicesImpl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    /**
     *
     * @return
     */
    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return projectServiceImpl.getAllProjects();
    }
}
