package com.example.demo.servicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.DemoApplication;
import com.example.demo.dto.WorkCalendarDTO;
import com.example.demo.dto.mappers.FromDOToDTO;
import com.example.demo.entities.WorkCalendar;
import com.example.demo.repository.WorkCalendarRepository;
import com.example.demo.tools.WorkCalendarDTOTools;
import com.example.demo.tools.WorkCalendarTools;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = DemoApplication.class)
@AutoConfigureMockMvc
public class WorkCalendarImplTest {
  @MockBean private WorkCalendarRepository workCalendarRepository;
  @Autowired private WorkCalendarServiceImpl workCalendarService;
  @MockBean private FromDOToDTO fromDOToDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllTasks() {
    // Given
    final WorkCalendar workCalendar = WorkCalendarTools.createWorkCalendar(1L, "WorkCalendar");
    final WorkCalendar workCalendar1 = WorkCalendarTools.createWorkCalendar(2L, "WorkCalendar");
    final WorkCalendarDTO workCalendarDTO =
        WorkCalendarDTOTools.createWorkCalendarDTO("WorkCalendar");
    final WorkCalendarDTO workCalendarDTO1 =
        WorkCalendarDTOTools.createWorkCalendarDTO("WorkCalendar1");
    final List<WorkCalendar> mockWorkCalenders = Arrays.asList(workCalendar, workCalendar1);

    // When
    when(workCalendarRepository.findAll()).thenReturn(mockWorkCalenders);
    when(fromDOToDTO.mapWorkCalendar(workCalendar)).thenReturn(workCalendarDTO);
    when(fromDOToDTO.mapWorkCalendar(workCalendar1)).thenReturn(workCalendarDTO1);
    final List<WorkCalendarDTO> workCalendarDTOS = workCalendarService.getAllWorkCalendars();

    // Then
    assertEquals(mockWorkCalenders.size(), workCalendarDTOS.size());
  }

  @Test
  public void testSearchTasks() {
    // Given
    final String keyword = "Oumaima";
    final WorkCalendar workCalendar = WorkCalendarTools.createWorkCalendar(1L, "WorkCalendar");
    final WorkCalendar workCalendar1 = WorkCalendarTools.createWorkCalendar(2L, "WorkCalendar");
    final WorkCalendarDTO workCalendarDTO =
        WorkCalendarDTOTools.createWorkCalendarDTO("WorkCalendar");
    final WorkCalendarDTO workCalendarDTO1 =
        WorkCalendarDTOTools.createWorkCalendarDTO("WorkCalendar1");
    final List<WorkCalendar> mockWorkCalenders = Arrays.asList(workCalendar, workCalendar1);

    // When
    when(workCalendarRepository.findByTag(keyword)).thenReturn(mockWorkCalenders);
    when(fromDOToDTO.mapWorkCalendar(workCalendar)).thenReturn(workCalendarDTO);
    when(fromDOToDTO.mapWorkCalendar(workCalendar1)).thenReturn(workCalendarDTO1);
    final List<WorkCalendarDTO> workCalendarDTOS = workCalendarService.searchWorkCalendars(keyword);

    // Then
    assertEquals(mockWorkCalenders.size(), workCalendarDTOS.size());
  }

  @Test
  public void testAddTask() {
    // Given
    final WorkCalendar workCalendar = WorkCalendarTools.createWorkCalendar(1L, "WorkCalendar");
    final WorkCalendarDTO workCalendarDTO =
        WorkCalendarDTOTools.createWorkCalendarDTO("WorkCalendar");
    // When
    when(workCalendarRepository.save(workCalendar)).thenReturn(workCalendar);
    when(fromDOToDTO.mapWorkCalendar(workCalendar)).thenReturn(workCalendarDTO);
    final WorkCalendarDTO resultWorkCalendarDTODTO =
        workCalendarService.addWorkCalendar(workCalendar);

    // Then
    assertEquals(workCalendarDTO, resultWorkCalendarDTODTO);
  }

  @Test
  public void testUpdateTask() {
    // Given
    final WorkCalendar workCalendar = WorkCalendarTools.createWorkCalendar(1L, "WorkCalendar");
    final WorkCalendarDTO workCalendarDTO =
        WorkCalendarDTOTools.createWorkCalendarDTO("WorkCalendar");
    // When
    when(workCalendarRepository.save(workCalendar)).thenReturn(workCalendar);
    when(fromDOToDTO.mapWorkCalendar(workCalendar)).thenReturn(workCalendarDTO);
    final WorkCalendarDTO resultWorkCalendarDTODTO =
        workCalendarService.updateWorkCalendar(workCalendar);

    // Then
    assertEquals(workCalendarDTO, resultWorkCalendarDTODTO);
  }
}
