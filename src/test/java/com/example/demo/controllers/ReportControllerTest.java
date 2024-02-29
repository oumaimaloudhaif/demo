package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controllers.request.ReportRequest;
import com.example.demo.controllers.response.ReportResponse;
import com.example.demo.dto.ReportDTO;
import com.example.demo.entities.Report;
import com.example.demo.servicesImpl.ReportServiceImpl;
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
    final ReportDTO reportDTO = new ReportDTO("report");
    final ReportDTO reportDTO1 = new ReportDTO("report");
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

  @Test
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
    ReportResponse reports = super.mapFromJson(content, ReportResponse.class);
    assertEquals(0, reports.getResult().size());
  }

  @Test
  public void fetchReportsWithNullKeywordReturnsEmptyList() throws Exception {
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
    final ReportDTO reportDTO = new ReportDTO("report");

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
    assertEquals(reportDTO.getTitle(), result.getTitle());
  }

  @Test
  public void updateReport() throws Exception {
    // Given
    final String uri = "/reports";
    Report report = new Report();
    report.setTitle("report");
    String inputJson = new ObjectMapper().writeValueAsString(report);
    final ReportDTO reportDTO = new ReportDTO("report");

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
    assertEquals(reportDTO.getTitle(), result.getTitle());
  }

  @Test
  public void getReportNotExistTest() throws Exception {
    // Given
    String uri = "/reports/30";

    // When
    when(reportServiceImpl.getReportById(30L)).thenReturn(null);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals("", content);
  }

  @Test
  public void getReportExistTest() throws Exception {
    // Given
    String uri = "/reports/1";
    final ReportDTO reportDTO = new ReportDTO("report");

    // When
    when(reportServiceImpl.getReportById(1L)).thenReturn(reportDTO);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    ReportDTO result = objectMapper.readValue(content, ReportDTO.class);
    assertEquals(reportDTO.getTitle(), result.getTitle());
  }

  @Test
  public void deleteReportNotExistTest() throws Exception {
    // Given
    String uri = "/reports/21";

    // When
    when(reportServiceImpl.deleteReportById(21L)).thenReturn(false);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.delete(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    Boolean actualValue = Boolean.valueOf(content);
    assertEquals(false, actualValue);
  }

  @Test
  public void deleteReportExistTest() throws Exception {
    // Given
    String uri = "/reports/1";

    // When
    when(reportServiceImpl.deleteReportById(1L)).thenReturn(true);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.delete(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    Boolean actualValue = Boolean.valueOf(content);
    assertEquals(true, actualValue);
  }
}
