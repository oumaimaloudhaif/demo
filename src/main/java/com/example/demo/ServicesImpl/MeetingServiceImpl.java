package com.example.demo.ServicesImpl;

import com.example.demo.Entities.Meeting;
import com.example.demo.Repository.MeetingRepository;
import com.example.demo.Services.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Meeting Service Implementation
 */
@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingRepository meetingRepository;

    /**
     *
     * @return
     */
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }
}
