package com.example.demo.Repository;

import com.example.demo.Entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Company Repository
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByName(String keyword);
}