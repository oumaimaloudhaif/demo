package com.example.demo.ServicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.DemoApplication;
import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Dto.ReportDTO;
import com.example.demo.Entities.Report;
import com.example.demo.Repository.ReportRepository;
import java.util.Arrays;

import java.util.List;

import com.example.demo.tools.ReportTools;
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
    @MockBean
    private ReportRepository ReportRepository;
    @Autowired
    private ReportServiceImpl ReportService;
    @MockBean private FromDOToDTO fromDOToDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllReports() {
        // Given
        final Report Report= ReportTools.createReport(1L, "Report");
        final Report Report1= ReportTools.createReport(2L, "Report1");
        final ReportDTO ReportDTO= new  ReportDTO( "Report");
        final ReportDTO Report1DTO= new  ReportDTO("Report1");
        final List<Report> mockedReports = Arrays.asList(Report,Report1);

        // When
        when(ReportRepository.findAll()).thenReturn(mockedReports);
        when(fromDOToDTO.MapReport(Report)).thenReturn(ReportDTO);
        when(fromDOToDTO.MapReport(Report1)).thenReturn(Report1DTO);
        final List<ReportDTO> Reports = ReportService.getAllReports();

        // Then
        assertEquals(mockedReports.size(), Reports.size());
    }

    @Test
    public void testSearchReports() {
        // Given
        final String keyword = "Oumaima";
        final Report Report= ReportTools.createReport(1L, "Report");
        final Report Report1= ReportTools.createReport(2L, "Report1");
        final ReportDTO ReportDTO= new  ReportDTO( "Report");
        final ReportDTO Report1DTO= new  ReportDTO("Report1");
        final List<Report> mockedReports = Arrays.asList(Report,Report1);

        // When
        when(ReportRepository.findByTitle(keyword)).thenReturn(mockedReports);
        when(fromDOToDTO.MapReport(Report)).thenReturn(ReportDTO);
        when(fromDOToDTO.MapReport(Report1)).thenReturn(Report1DTO);
        List<ReportDTO> Reports = ReportService.searchReports(keyword);

        // Then
        assertEquals(mockedReports.size(), Reports.size());
    }

    @Test
    public void testAddReport() {
        // Given
        final Report inputReport= ReportTools.createReport(1L, "Report");
        final ReportDTO expectedReportDTO = new ReportDTO("Report1");

        // When
        when(ReportRepository.save(inputReport)).thenReturn(inputReport);
        when(fromDOToDTO.MapReport(inputReport)).thenReturn(expectedReportDTO);
        final ReportDTO resultReportDTO = ReportService.addReport(inputReport);

        // Then
        assertEquals(expectedReportDTO, resultReportDTO);
    }

    @Test
    public void testUpdateReport() {
        // Given
        final Report updatedReport= ReportTools.createReport(1L, "Report");
        final ReportDTO expectedReportDTO = new ReportDTO("Report1");

        // When
        when(ReportRepository.save(updatedReport)).thenReturn(updatedReport);
        when(fromDOToDTO.MapReport(updatedReport)).thenReturn(expectedReportDTO);
        final ReportDTO resultReportDTO = ReportService.addReport(updatedReport);

        // Then
        assertEquals(expectedReportDTO, resultReportDTO);
    }
}
