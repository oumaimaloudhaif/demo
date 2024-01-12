package com.example.demo.Repository;

import com.example.demo.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project Repository
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByName(String keyword);
}
