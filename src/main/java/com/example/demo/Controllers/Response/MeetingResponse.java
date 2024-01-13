package com.example.demo.Controllers.Response;

import com.example.demo.Dto.MeetingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingResponse {
    List<MeetingDTO> result;
}