package com.example.demo.servicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.DemoApplication;
import com.example.demo.dto.ReportDTO;
import com.example.demo.dto.mappers.FromDOToDTO;
import com.example.demo.entities.Report;
import com.example.demo.repository.ReportRepository;
import com.example.demo.tools.ReportDTOTools;
import com.example.demo.tools.ReportTools;
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
public class ReportServiceImplTest {
  @MockBean private ReportRepository ReportRepository;
  @Autowired private ReportServiceImpl ReportService;
  @MockBean private FromDOToDTO fromDOToDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllReports() {
    // Given
    final Report Report = ReportTools.createReport(1L, "Report");
    final Report Report1 = ReportTools.createReport(2L, "Report1");
    final ReportDTO ReportDTO = ReportDTOTools.createReportDTO("Report");
    final ReportDTO Report1DTO = ReportDTOTools.createReportDTO("Report1");
    final List<Report> mockedReports = Arrays.asList(Report, Report1);

    // When
    when(ReportRepository.findAll()).thenReturn(mockedReports);
    when(fromDOToDTO.mapReport(Report)).thenReturn(ReportDTO);
    when(fromDOToDTO.mapReport(Report1)).thenReturn(Report1DTO);
    final List<ReportDTO> Reports = ReportService.getAllReports();

    // Then
    assertEquals(mockedReports.size(), Reports.size());
  }

  @Test
  public void testSearchReports() {
    // Given
    final String keyword = "Oumaima";
    final Report Report = ReportTools.createReport(1L, "Report");
    final Report Report1 = ReportTools.createReport(2L, "Report1");
    final ReportDTO ReportDTO = ReportDTOTools.createReportDTO("Report");
    final ReportDTO Report1DTO = ReportDTOTools.createReportDTO("Report1");
    final List<Report> mockedReports = Arrays.asList(Report, Report1);

    // When
    when(ReportRepository.findByTitle(keyword)).thenReturn(mockedReports);
    when(fromDOToDTO.mapReport(Report)).thenReturn(ReportDTO);
    when(fromDOToDTO.mapReport(Report1)).thenReturn(Report1DTO);
    List<ReportDTO> Reports = ReportService.searchReports(keyword);

    // Then
    assertEquals(mockedReports.size(), Reports.size());
  }

  @Test
  public void testAddReport() {
    // Given
    final Report inputReport = ReportTools.createReport(1L, "Report");
    final ReportDTO expectedReportDTO = ReportDTOTools.createReportDTO("Report1");

    // When
    when(ReportRepository.save(inputReport)).thenReturn(inputReport);
    when(fromDOToDTO.mapReport(inputReport)).thenReturn(expectedReportDTO);
    final ReportDTO resultReportDTO = ReportService.addReport(inputReport);

    // Then
    assertEquals(expectedReportDTO, resultReportDTO);
  }

  @Test
  public void testUpdateReport() {
    // Given
    final Report updatedReport = ReportTools.createReport(1L, "Report");
    final ReportDTO expectedReportDTO = ReportDTOTools.createReportDTO("Report1");

    // When
    when(ReportRepository.save(updatedReport)).thenReturn(updatedReport);
    when(fromDOToDTO.mapReport(updatedReport)).thenReturn(expectedReportDTO);
    final ReportDTO resultReportDTO = ReportService.updateReport(updatedReport);

    // Then
    assertEquals(expectedReportDTO, resultReportDTO);
  }
}
