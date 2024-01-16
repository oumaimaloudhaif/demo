package com.example.demo.ServicesImpl;

import com.example.demo.DemoApplication;
import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Dto.ProjectDTO;
import com.example.demo.Entities.Employee;
import com.example.demo.Entities.Project;
import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;
import com.example.demo.Repository.ProjectRepository;
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
public class ProjectServiceImplTest {
    @Mock
    private ProjectRepository projectRepository;
    @InjectMocks
    private ProjectServiceImpl projectService;
    @Mock
    private FromDOToDTO fromDOToDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    Date date=new Date(2024, Calendar.JANUARY,13);

    @Test
    public void testGetAllProjects() {
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI),
                new Employee(2L, "Oumaima", 1200, Gender.FEMALE, ContractType.CDI)
        );
        List<Project> mockedProjects = Arrays.asList(
                new Project(1L, "description",employees, date, date),
                new Project(2L, "description",employees,date,date)
        );
        when(projectRepository.findAll()).thenReturn(mockedProjects);
        List<ProjectDTO> Projects = projectService.getAllProjects();
        assertEquals(mockedProjects.size(), Projects.size());
    }
    @Test
    public void testSearchProjects() {
        String keyword = "Oumaima";
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI),
                new Employee(2L, "Oumaima", 1200, Gender.FEMALE, ContractType.CDI)
        );
        List<Project> mockedProjects = Arrays.asList(
                new Project(1L, "description",employees, date, date),
                new Project(2L, "description",employees,date,date)
        );
        when(projectRepository.findByName(keyword)).thenReturn(mockedProjects);
        List<ProjectDTO> Projects = projectService.searchProject(keyword);
        assertEquals(mockedProjects.size(), Projects.size());
    }
    @Test
    public void testAddProject() {
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI),
                new Employee(2L, "Oumaima", 1200, Gender.FEMALE, ContractType.CDI)
        );
        Project inputProject = new Project(1L, "description",employees, date, date);
        Project savedProject = new Project(1L, "description",employees, date, date);
        ProjectDTO expectedProjectDTO = new ProjectDTO("Project1",employees);

        when(projectRepository.save(inputProject)).thenReturn(savedProject);
        when(fromDOToDTO.MapProject(savedProject)).thenReturn(expectedProjectDTO);

        ProjectDTO resultProjectDTO = projectService.addProject(inputProject);

        assertEquals(expectedProjectDTO, resultProjectDTO);
    }
    @Test
   public void testUpdateProject() {
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI),
                new Employee(2L, "Oumaima", 1200, Gender.FEMALE, ContractType.CDI)
        );
        Project inputProject = new Project(1L, "description",employees, date, date);
        Project updatedProject = new Project(1L, "description",employees, date, date);
        ProjectDTO expectedProjectDTO = new ProjectDTO("Project1",employees);

        when(projectRepository.save(inputProject)).thenReturn(updatedProject);
        when(fromDOToDTO.MapProject(updatedProject)).thenReturn(expectedProjectDTO);

        ProjectDTO resultProjectDTO = projectService.addProject(inputProject);

        assertEquals(expectedProjectDTO, resultProjectDTO);
    }
}