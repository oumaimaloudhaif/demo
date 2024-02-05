package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.MeetingResponse;
import com.example.demo.dto.MeetingDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeetingMapperTest {
  @Autowired private MeetingMapper meetingMapper;
  @Test
  public void toMeetingResponseTest() {
    // Given

    final MeetingDTO meetingDTO = new MeetingDTO("meeting");
    final MeetingDTO meetingDTO1 = new MeetingDTO("meeting");
    List<MeetingDTO> meetingDTOS = List.of(meetingDTO, meetingDTO1);

    // When
    MeetingResponse result = meetingMapper.toMeetingResponse(meetingDTOS);

    // Then
    Assert.assertEquals(result.getResult().size(), 2);
  }
}
