package com.example.demo.Repository;

import com.example.demo.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Project Repository
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
