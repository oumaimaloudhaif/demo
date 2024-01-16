package com.example.demo.ServicesImpl;

import com.example.demo.DemoApplication;
import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Dto.ReportDTO;
import com.example.demo.Entities.Report;
import com.example.demo.Repository.ReportRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DemoApplication.class)
@AutoConfigureMockMvc
public class ReportServiceImplTest {
    @Mock
    private ReportRepository reportRepository;
    @InjectMocks
    private ReportServiceImpl reportService;
    @Mock
    private FromDOToDTO fromDOToDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    Date date=new Date(2024, Calendar.JANUARY,13);
    @Test
    public void testGetAllreports() {
        List<Report> mockedReports = Arrays.asList(
                new Report(1L,"Report1", "content1", date, date),
                new Report(2L,"Report2", "content2", date, date)
        );
        when(reportRepository.findAll()).thenReturn(mockedReports);
        List<ReportDTO> Reports = reportService.getAllReports();
        assertEquals(mockedReports.size(), Reports.size());
    }
    @Test
    public void testSearchreports() {
        String keyword = "Oumaima";
        List<Report> mockedReports = Arrays.asList(
                new Report(1L,"Report1", "content1", date, date),
                new Report(2L,"Report2", "content2", date, date)
        );
        when(reportRepository.findByTitle(keyword)).thenReturn(mockedReports);
        List<ReportDTO> Reports = reportService.searchReports(keyword);
        assertEquals(mockedReports.size(), Reports.size());
    }
    @Test
    public void testAddreport() {
        Report inputReport = new Report(1L,"Report1", "content1", date, date);
        Report savedReport = new Report(1L,"Report1", "content1", date, date);
        ReportDTO expectedReportDTO = new ReportDTO("Report1", "description");

        when(reportRepository.save(inputReport)).thenReturn(savedReport);
        when(fromDOToDTO.MapReport(savedReport)).thenReturn(expectedReportDTO);

        ReportDTO resultReportDTO = reportService.addReport(inputReport);

        assertEquals(expectedReportDTO, resultReportDTO);
    }
    @Test
   public void testUpdatereport() {
        Report inputReport = new Report(1L,"Report1", "content1", date, date);
        Report savedReport = new Report(1L,"Report1", "content1", date, date);
        ReportDTO expectedReportDTO = new ReportDTO("Report1", "description");

        when(reportRepository.save(inputReport)).thenReturn(savedReport);
        when(fromDOToDTO.MapReport(savedReport)).thenReturn(expectedReportDTO);

        ReportDTO resultReportDTO = reportService.addReport(inputReport);

        assertEquals(expectedReportDTO, resultReportDTO);
    }
}