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

import com.example.demo.tools.TaskDTOTools;
import com.example.demo.tools.TaskTools;
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
public class TaskServiceImplTest {
  @MockBean
  private TaskRepository taskRepository;
  @Autowired
  private TaskServiceImpl taskService;
  @MockBean private FromDOToDTO fromDOToDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllTasks() {
    // Given
    final Task task= TaskTools.createTask(1L,"Task", "description", TaskStatus.IN_PROGRESS, Priority.HIGH);
    final Task task1= TaskTools.createTask(2L,"Task1","description",  TaskStatus.IN_PROGRESS, Priority.HIGH);
    final TaskDTO taskDTO= TaskDTOTools.createTaskDTO("Task","description",TaskStatus.IN_PROGRESS, Priority.HIGH );
    final TaskDTO task1DTO= TaskDTOTools.createTaskDTO("Task1","description", TaskStatus.IN_PROGRESS, Priority.HIGH );
    final List<Task> mockedTasks = Arrays.asList(task,task1);

    // When
    when(taskRepository.findAll()).thenReturn(mockedTasks);
    when(fromDOToDTO.MapTask(task)).thenReturn(taskDTO);
    when(fromDOToDTO.MapTask(task)).thenReturn(task1DTO);
    final List<TaskDTO> Tasks = taskService.getAllTasks();

    // Then
    assertEquals(mockedTasks.size(), Tasks.size());
  }

  @Test
  public void testSearchTasks() {
    // Given
    final String keyword = "Oumaima";
    final Task task= TaskTools.createTask(1L,"Task", "description", TaskStatus.IN_PROGRESS, Priority.HIGH);
    final Task task1= TaskTools.createTask(2L,"Task1","description",  TaskStatus.IN_PROGRESS, Priority.HIGH);
    final TaskDTO taskDTO= TaskDTOTools.createTaskDTO("Task","description",TaskStatus.IN_PROGRESS, Priority.HIGH );
    final TaskDTO task1DTO= TaskDTOTools.createTaskDTO("Task1","description", TaskStatus.IN_PROGRESS, Priority.HIGH );
    final List<Task> mockedTasks = Arrays.asList(task,task1);

    // When
    when(taskRepository.findByName(keyword)).thenReturn(mockedTasks);
    when(fromDOToDTO.MapTask(task)).thenReturn(taskDTO);
    when(fromDOToDTO.MapTask(task)).thenReturn(task1DTO);
    final List<TaskDTO> Tasks = taskService.searchTasks(keyword);

    // Then
    assertEquals(mockedTasks.size(), Tasks.size());
  }

  @Test
  public void testAddTask() {
    // Given
    final Task inputTask= TaskTools.createTask(1L,"Task", "description", TaskStatus.IN_PROGRESS, Priority.HIGH);
    final TaskDTO expectedTaskDTO= TaskDTOTools.createTaskDTO("Task","description",TaskStatus.IN_PROGRESS, Priority.HIGH );
    // When
    when(taskRepository.save(inputTask)).thenReturn(inputTask);
    when(fromDOToDTO.MapTask(inputTask)).thenReturn(expectedTaskDTO);
    final TaskDTO resultTaskDTO = taskService.addTask(inputTask);

    // Then
    assertEquals(expectedTaskDTO, resultTaskDTO);
  }

  @Test
  public void testUpdateTask() {
    // Given
    final Task inputTask= TaskTools.createTask(1L,"Task", "description", TaskStatus.IN_PROGRESS, Priority.HIGH);
    final TaskDTO expectedTaskDTO= TaskDTOTools.createTaskDTO("Task","description",TaskStatus.IN_PROGRESS, Priority.HIGH );

    // When
    when(taskRepository.save(inputTask)).thenReturn(inputTask);
    when(fromDOToDTO.MapTask(inputTask)).thenReturn(expectedTaskDTO);
    final TaskDTO resultTaskDTO = taskService.addTask(inputTask);

    // Then
    assertEquals(expectedTaskDTO, resultTaskDTO);
  }
}
