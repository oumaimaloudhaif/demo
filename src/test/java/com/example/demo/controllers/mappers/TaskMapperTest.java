package com.example.demo.controllers.mappers;

import com.example.demo.controllers.response.TaskResponse;
import com.example.demo.dto.TaskDTO;
import com.example.demo.enums.Priority;
import com.example.demo.enums.TaskStatus;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {
  @Autowired private TaskMapper taskMapper;

  @Test
  public void toAddressResponseTest() {
    // Given

    final TaskDTO taskDTO =
        new TaskDTO("task", "description", Priority.HIGH, TaskStatus.IN_PROGRESS);
    final TaskDTO taskDTO1 =
        new TaskDTO("task", "description", Priority.HIGH, TaskStatus.IN_PROGRESS);
    List<TaskDTO> taskDTO1s = List.of(taskDTO, taskDTO1);

    // When
    TaskResponse result = taskMapper.toTasksResponse(taskDTO1s);

    // Then
    Assert.assertEquals(result.getResult().size(), 2);
  }
}
