package com.example.demo.Repository;

import com.example.demo.Entities.Task;
import com.example.demo.Enums.Priority;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Task Repository */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByPriority(Priority priority);

  List<Task> findByName(String keyword);
}
