package com.example.demo.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.Controllers.Request.ReportRequest;
import com.example.demo.Controllers.Response.ReportResponse;
import com.example.demo.Dto.ReportDTO;
import com.example.demo.Entities.Report;
import com.example.demo.ServicesImpl.ReportServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class ReportControllerTest extends AbstractTest {

  @MockBean ReportServiceImpl reportServiceImpl;
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void getAllReportsTestWhenReportExist() throws Exception {
    // Given
    final String uri = "/reports";
    final ReportDTO reportDTO = new ReportDTO("report","content");
    final ReportDTO reportDTO1 = new ReportDTO("report","content");
    final List<ReportDTO> listOfReports = List.of(reportDTO, reportDTO1);

    // When
    when(reportServiceImpl.getAllReports()).thenReturn(listOfReports);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    ReportResponse reports = super.mapFromJson(content, ReportResponse.class);
    assertEquals(2, reports.getResult().size());
  }

  @Test
  public void getAllReportsTestWhenNoReportExist() throws Exception {
    // Given
    final String uri = "/reports";
    final List<ReportDTO> listOfReports = List.of();

    // When
    when(reportServiceImpl.getAllReports()).thenReturn(listOfReports);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    ReportResponse reports = super.mapFromJson(content, ReportResponse.class);
    assertEquals(0, reports.getResult().size());
  }

  @Test
  public void getAllReportsTestWrongPath() throws Exception {
    // given
    final String uri = "/reportss";

    // when
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(404, status);
  }

  public void searchReportTestWhenKeywordIsNull() throws Exception {
    // given
    final String uri = "/reports";
    // when
    when(reportServiceImpl.searchReports(null)).thenReturn(List.of());
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.get(uri)
                    .param("keyword", (String) null)
                    .accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    ReportDTO[] reports = super.mapFromJson(content, ReportDTO[].class);
    assertEquals(0, reports.length);
  }

  @Test
  public void fetchReports_WithNullKeyword_ReturnsEmptyList() throws Exception {
    // Given
    final String uri = "/reports";
    ReportRequest reportRequest = new ReportRequest();
    reportRequest.setKeyword("");
    final List<ReportDTO> listOfReports = List.of();

    // When
    when(reportServiceImpl.getAllReports()).thenReturn(listOfReports);
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(reportRequest.getKeyword())))
            .andExpect(status().isOk())
            .andReturn();

    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    ReportResponse result = objectMapper.readValue(content, ReportResponse.class);
    assertEquals(0, result.getResult().size());
  }

  @Test
  public void addReportTest() throws Exception {

    // Given
    final String uri = "/reports";
    Report report = new Report();
    report.setTitle("report");
    String inputJson = new ObjectMapper().writeValueAsString(report);
    final ReportDTO reportDTO = new ReportDTO("report","content");

    // When
    when(reportServiceImpl.addReport(any(Report.class))).thenReturn(reportDTO);
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    ReportDTO result = objectMapper.readValue(content, ReportDTO.class);
    assertEquals(reportDTO.title(), result.title());
  }

  @Test
  public void updateReport() throws Exception {
    // Given
    final String uri = "/reports";
    Report report = new Report();
    report.setTitle("report");
    String inputJson = new ObjectMapper().writeValueAsString(report);
    final ReportDTO reportDTO = new ReportDTO("report","content");

    // When
    when(reportServiceImpl.updateReport(any(Report.class))).thenReturn(reportDTO);
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.put(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    ReportDTO result = objectMapper.readValue(content, ReportDTO.class);
    assertEquals(reportDTO.title(), result.title());
  }
}
