package com.example.demo.repository;

import com.example.demo.entities.Department;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Department Repository */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
  List<Department> findByName(String keyword);
}
