package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.ReportResponse;
import com.example.demo.dto.ReportDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportMapperTest {
  @Autowired private ReportMapper reportMapper;
  @Test
  public void toAddressResponseTest() {
    // Given

    final ReportDTO reportDTO = new ReportDTO("report");
    final ReportDTO reportDTO1 = new ReportDTO("report");
    List<ReportDTO> reportDTOs= List.of(reportDTO, reportDTO1);

    // When
    ReportResponse result = reportMapper.toReportResponse(reportDTOs);

    // Then
    Assert.assertEquals(result.getResult().size(), 2);
  }
}
