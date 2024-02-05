package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.ProjectResponse;
import com.example.demo.dto.ProjectDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectMapperTest {
  @Autowired private ProjectMapper projectMapper;
  @Test
  public void toMeetingResponseTest() {
    // Given

    final ProjectDTO project = new ProjectDTO("project");
    final ProjectDTO project2 = new ProjectDTO("project1");
    List<ProjectDTO> projectDtOs = List.of(project, project2);

    // When
    ProjectResponse result = projectMapper.toProjectResponse(projectDtOs);

    // Then
    Assert.assertEquals(result.getResult().size(), 2);
  }
}
