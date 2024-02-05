package com.example.demo.repository;

import com.example.demo.entities.Report;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Report Repository */
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
  List<Report> findByTitle(String keyword);
}
