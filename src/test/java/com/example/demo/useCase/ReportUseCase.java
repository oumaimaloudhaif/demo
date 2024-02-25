package com.example.demo.useCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controllers.AbstractTest;
import com.example.demo.controllers.request.ReportRequest;
import com.example.demo.controllers.response.ReportResponse;
import com.example.demo.dto.ReportDTO;
import com.example.demo.entities.Report;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


class ReportUseCase extends AbstractTest {
  @Autowired
  private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void fetchAllReportsTest() throws Exception {
    // Given
    String url = "/reports";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    ReportResponse reports = super.mapFromJson(content, ReportResponse.class);
    Assertions.assertEquals(2, reports.getResult().size());
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
    assertEquals(2, reports.getResult().size());
  }

  @Test
  public void fetchReportsWithNullKeywordReturnsEmptyList() throws Exception {
    // Given
    final String uri = "/reports";
    ReportRequest reportRequest = new ReportRequest();
    reportRequest.setKeyword("");

    // When
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
    assertEquals(2, result.getResult().size());
  }

  @Test
  public void addReportTest() throws Exception {

    // Given
    final String uri = "/reports";
    Report report = new Report();
    report.setTitle("test");
    String inputJson = new ObjectMapper().writeValueAsString(report);

    // When
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
    assertEquals("test", result.getTitle());
  }

  @Test
  public void updateReport() throws Exception {
    // Given
    final String uri = "/reports";
    Report report = new Report();
    report.setTitle("test1");
    String inputJson = new ObjectMapper().writeValueAsString(report);

    // When
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
    assertEquals("test1", result.getTitle());
  }

  @Test
  public void findReportById() throws Exception {
    // Given
    final String uri = "/reports/2";

    // When
    MvcResult mvcResult =
            mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                    .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    ReportDTO result = objectMapper.readValue(content, ReportDTO.class);
    assertEquals("report", result.getTitle());
  }

  @Test
  public void deleteReportNotExistTest() throws Exception {
    // Given
    String uri = "/reports/29";

    // when
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
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

    // when
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    Boolean actualValue = Boolean.valueOf(content);
    assertEquals(true, actualValue);
  }

}
