package com.example.demo.repository;

import com.example.demo.entities.WorkCalendar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** WorkCalendar Repository */
@Repository
public interface WorkCalendarRepository extends JpaRepository<WorkCalendar, Long> {
  List<WorkCalendar> findByTag(String keyword);
}
