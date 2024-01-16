package com.example.demo.ServicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.DemoApplication;
import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Dto.TaskDTO;
import com.example.demo.Entities.Task;
import com.example.demo.Enums.Priority;
import com.example.demo.Enums.TaskStatus;
import com.example.demo.Repository.TaskRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = DemoApplication.class)
@AutoConfigureMockMvc
public class TaskServiceImplTest {
  @Mock private TaskRepository taskRepository;
  @InjectMocks private TaskServiceImpl taskService;
  @Mock private FromDOToDTO fromDOToDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllTasks() {
    List<Task> mockedTasks =
        Arrays.asList(
            new Task("Task1", "description", TaskStatus.IN_PROGRESS, Priority.HIGH),
            new Task("Task2", "description", TaskStatus.COMPLETED, Priority.HIGH));
    when(taskRepository.findAll()).thenReturn(mockedTasks);
    List<TaskDTO> Tasks = taskService.getAllTasks();
    assertEquals(mockedTasks.size(), Tasks.size());
  }

  @Test
  public void testSearchTasks() {
    String keyword = "Oumaima";
    List<Task> mockedTasks =
        Arrays.asList(
            new Task("Task1", "description", TaskStatus.IN_PROGRESS, Priority.HIGH),
            new Task("Task2", "description", TaskStatus.COMPLETED, Priority.HIGH));
    when(taskRepository.findByName(keyword)).thenReturn(mockedTasks);
    List<TaskDTO> Tasks = taskService.searchTasks(keyword);
    assertEquals(mockedTasks.size(), Tasks.size());
  }

  @Test
  public void testAddTask() {
    Task inputTask = new Task("Task1", "description", TaskStatus.IN_PROGRESS, Priority.HIGH);
    Task savedTask = new Task("Task1", "description", TaskStatus.IN_PROGRESS, Priority.HIGH);
    TaskDTO expectedTaskDTO =
        new TaskDTO("Task1", "description", Priority.HIGH, TaskStatus.IN_PROGRESS);

    when(taskRepository.save(inputTask)).thenReturn(savedTask);
    when(fromDOToDTO.MapTask(savedTask)).thenReturn(expectedTaskDTO);

    TaskDTO resultTaskDTO = taskService.addTask(inputTask);

    assertEquals(expectedTaskDTO, resultTaskDTO);
  }

  @Test
  public void testUpdateTask() {
    Task inputTask = new Task("Task1", "description", TaskStatus.IN_PROGRESS, Priority.HIGH);
    Task updatedTask = new Task("Task1", "description", TaskStatus.IN_PROGRESS, Priority.HIGH);
    TaskDTO expectedTaskDTO =
        new TaskDTO("Task1", "description", Priority.HIGH, TaskStatus.IN_PROGRESS);

    when(taskRepository.save(inputTask)).thenReturn(updatedTask);
    when(fromDOToDTO.MapTask(updatedTask)).thenReturn(expectedTaskDTO);

    TaskDTO resultTaskDTO = taskService.addTask(inputTask);

    assertEquals(expectedTaskDTO, resultTaskDTO);
  }
}
