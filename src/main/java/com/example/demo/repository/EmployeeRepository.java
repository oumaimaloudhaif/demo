package com.example.demo.repository;

import com.example.demo.entities.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Employee Repository */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  List<Employee> findByNameContaining(String keyword);
}
