package com.example.demo.ServicesImpl;

import com.example.demo.Entities.Report;
import com.example.demo.Repository.ReportRepository;
import com.example.demo.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Report Service Impl
 */
@Service
public class ReportServiceImpl  implements ReportService {
    @Autowired
    private ReportRepository reportRepository;

    /**
     *
     * @return all Reports
     */
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
}

