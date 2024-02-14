package com.example.demo.repository;

import com.example.demo.entities.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Task Repository */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByName(String keyword);
}
