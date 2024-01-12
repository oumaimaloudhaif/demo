package com.example.demo.Repository;

import com.example.demo.Entities.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Meeting Repository
 */
@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByTitle(String keyword);
}
