package com.example.demo.Repository;

import com.example.demo.Entities.Department;
import com.example.demo.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
