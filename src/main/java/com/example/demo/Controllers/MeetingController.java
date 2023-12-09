package com.example.demo.Controllers;

import com.example.demo.Entities.Meeting;
import com.example.demo.ServicesImpl.MeetingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeetingController {
    @Autowired
    private MeetingServiceImpl meetingServiceImpl;

    /**
     *
     * @return
     */
    @GetMapping("/meetings")
    public List<Meeting> getAllMeetings() {
        return meetingServiceImpl.getAllMeetings();
    }

}
