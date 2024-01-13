package com.example.demo.ServicesImpl;

import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Dto.MeetingDTO;
import com.example.demo.Entities.Meeting;
import com.example.demo.Repository.MeetingRepository;
import com.example.demo.Services.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Meeting Service Implementation
 */
@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private  FromDOToDTO fromDOToDTO;
    /**
     *
     * @return
     */
    public List<MeetingDTO> getAllMeetings() {
        List<Meeting> meetings=meetingRepository.findAll();
        List<MeetingDTO> meetingDTOS=new ArrayList<>();
        meetings.forEach(meeting -> {
            MeetingDTO meetingDTO=fromDOToDTO.MapMeeting(meeting);
            meetingDTOS.add(meetingDTO);
        });
        return meetingDTOS;
    }
    public  List<MeetingDTO> searchMeeting(String keyword) {
        List<Meeting> meetings=meetingRepository.findByTitle(keyword);
        List<MeetingDTO> meetingDTOS=new ArrayList<>();
        meetings.forEach(meeting -> {
            MeetingDTO meetingDTO=fromDOToDTO.MapMeeting(meeting);
            meetingDTOS.add(meetingDTO);
        });
        return meetingDTOS;
    }

    public  Meeting addMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    public  Meeting updateMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

}
