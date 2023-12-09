package com.example.demo.Controllers;

import com.example.demo.Entities.Report;
import com.example.demo.ServicesImpl.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {
    @Autowired
    private ReportServiceImpl reportServiceImpl;

    /**
     *
     * @return
     */
    @GetMapping("/reports")
    public List<Report> getAllReports() {
        return reportServiceImpl.getAllReports();
    }


}

