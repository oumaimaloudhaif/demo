package com.example.demo.Repository;

import com.example.demo.Entities.Task;
import com.example.demo.Enums.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByPriority(Priority priority);
}
