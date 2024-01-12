package com.example.demo.Repository;

import com.example.demo.Entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Report Repository
 */
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByTitle(String keyword);
}

