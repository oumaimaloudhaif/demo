package com.example.demo.repository;

import com.example.demo.entities.Meeting;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Meeting Repository */
@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
  List<Meeting> findByTitle(String keyword);
}
