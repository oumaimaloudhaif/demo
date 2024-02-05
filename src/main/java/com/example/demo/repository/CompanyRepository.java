package com.example.demo.repository;

import com.example.demo.entities.Company;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Company Repository */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
  List<Company> findByName(String keyword);
}
