package pm.little.courseservice.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pm.little.api.models.ProjectBlueprint;
import pm.little.api.models.ProjectInstance;
import pm.little.api.models.dto.ProjectDTO;
import pm.little.api.models.enums.DifficultyEnum;
import pm.little.api.models.enums.StatusEnum;
import pm.little.api.models.enums.StyleEnum;
import pm.little.api.models.ids.ProjectInstanceId;
import pm.little.api.repositories.DayBlueprintRepository;
import pm.little.api.repositories.ProjectBlueprintRepository;
import pm.little.api.repositories.ProjectDaysMapperRepository;
import pm.little.api.repositories.ProjectInstanceRepository;
import pm.little.courseservice.exceptions.ProjectBlueprintNotFoundException;
import pm.little.courseservice.exceptions.ProjectInstanceNotFoundException;
import pm.little.courseservice.implementation.ProjectServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceInstanceTest {

    @Mock
    private ProjectBlueprintRepository projectBlueprintRepository;

    @Mock
    private ProjectDaysMapperRepository projectDaysMapperRepository;

    @Mock
    private ProjectInstanceRepository projectInstanceRepository;

    @Mock
    private DayBlueprintRepository dayBlueprintRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private UUID projectBlueprintUuid;
    private UUID userUuid;
    private ProjectBlueprint projectBlueprint;
    private ProjectInstance projectInstance;
    private ProjectInstanceId projectInstanceId;

    @BeforeEach
    void setUp() {
        projectBlueprintUuid = UUID.randomUUID();
        userUuid = UUID.randomUUID();
        
        projectBlueprint = new ProjectBlueprint()
                .projectBlueprintUuid(projectBlueprintUuid)
                .title("Test Project")
                .description("Test Description")
                .difficulty(DifficultyEnum.MEDIUM)
                .style(StyleEnum.DIY)
                .posterUrl("https://example.com/poster.jpg")
                .welcomeVideoUrl("https://example.com/welcome.mp4");
        
        projectInstanceId = new ProjectInstanceId(projectBlueprintUuid, userUuid);
        projectInstance = new ProjectInstance();
        projectInstance.setId(projectInstanceId);
        projectInstance.setStatus(StatusEnum.IN_PROGRESS);
    }

    @Test
    @DisplayName("Should create project instance successfully")
    void shouldCreateProjectInstance() {
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(true);
        when(projectBlueprintRepository.findById(projectBlueprintUuid)).thenReturn(Optional.of(projectBlueprint));
        when(projectInstanceRepository.findById(projectInstanceId)).thenReturn(Optional.empty());
        when(projectInstanceRepository.save(any(ProjectInstance.class))).thenReturn(projectInstance);

        ProjectDTO result = projectService.createProjectInstance(projectInstance);

        assertNotNull(result);
        assertEquals(projectBlueprintUuid, result.getBlueprint().getProjectBlueprintUuid());
        assertEquals(projectBlueprintUuid, result.getInstance().getId().getProjectBlueprintUuid());
        assertEquals(userUuid, result.getInstance().getId().getUserUuid());
        assertEquals(StatusEnum.IN_PROGRESS, result.getInstance().getStatus());
        verify(projectInstanceRepository, times(1)).save(any(ProjectInstance.class));
    }

    @Test
    @DisplayName("Should throw exception when creating project instance with non-existent blueprint")
    void shouldThrowExceptionWhenCreatingProjectInstanceWithNonExistentBlueprint() {
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(false);

        assertThrows(ProjectBlueprintNotFoundException.class, () -> 
            projectService.createProjectInstance(projectInstance));
    }

    @Test
    @DisplayName("Should get project instance by IDs")
    void shouldGetProjectInstance() {
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(true);
        when(projectBlueprintRepository.findById(projectBlueprintUuid)).thenReturn(Optional.of(projectBlueprint));
        when(projectInstanceRepository.existsById(projectInstanceId)).thenReturn(true);
        when(projectInstanceRepository.findById(projectInstanceId)).thenReturn(Optional.of(projectInstance));

        ProjectDTO result = projectService.getProjectInstance(projectBlueprintUuid, userUuid);

        assertNotNull(result);
        assertEquals(projectBlueprintUuid, result.getBlueprint().getProjectBlueprintUuid());
        assertEquals(userUuid, result.getInstance().getId().getUserUuid());
    }

    @Test
    @DisplayName("Should throw exception when project instance not found")
    void shouldThrowExceptionWhenProjectInstanceNotFound() {
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(true);
        when(projectInstanceRepository.existsById(projectInstanceId)).thenReturn(false);

        assertThrows(ProjectInstanceNotFoundException.class, () -> 
            projectService.getProjectInstance(projectBlueprintUuid, userUuid));
    }

    @Test
    @DisplayName("Should get user project instances as DTO with pagination")
    void shouldGetUserProjectInstancesAsDTO() {
        List<ProjectInstance> instances = Arrays.asList(projectInstance);
        Pageable pageable = PageRequest.of(0, 10);
        
        when(projectInstanceRepository.findById_UserUuid(userUuid, pageable)).thenReturn(new PageImpl<>(instances));
        when(projectBlueprintRepository.findById(projectBlueprintUuid)).thenReturn(Optional.of(projectBlueprint));

        List<ProjectDTO> result = projectService.getUserProjectInstancesAsDTO(userUuid, 10, 0);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(projectBlueprintUuid, result.get(0).getBlueprint().getProjectBlueprintUuid());
        assertEquals(userUuid, result.get(0).getInstance().getId().getUserUuid());
    }

    @Test
    @DisplayName("Should update project instance successfully")
    void shouldUpdateProjectInstance() {
        ProjectInstance updatedInstance = new ProjectInstance();
        updatedInstance.setId(projectInstanceId);
        updatedInstance.setStatus(StatusEnum.COMPLETED);
        
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(true);
        when(projectBlueprintRepository.findById(projectBlueprintUuid)).thenReturn(Optional.of(projectBlueprint));
        when(projectInstanceRepository.existsById(projectInstanceId)).thenReturn(true);
        when(projectInstanceRepository.findById(projectInstanceId)).thenReturn(Optional.of(projectInstance));
        when(projectInstanceRepository.save(any(ProjectInstance.class))).thenReturn(updatedInstance);

        ProjectDTO result = projectService.updateProjectInstance(projectBlueprintUuid, userUuid, updatedInstance);

        assertNotNull(result);
        assertEquals(StatusEnum.COMPLETED, result.getInstance().getStatus());
        verify(projectInstanceRepository, times(1)).save(any(ProjectInstance.class));
    }

    @Test
    @DisplayName("Should delete project instance successfully")
    void shouldDeleteProjectInstance() {
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(true);
        when(projectInstanceRepository.existsById(projectInstanceId)).thenReturn(true);
        doNothing().when(projectInstanceRepository).deleteById(projectInstanceId);

        projectService.deleteProjectInstance(projectBlueprintUuid, userUuid);

        verify(projectInstanceRepository, times(1)).deleteById(projectInstanceId);
    }

    @Test
    @DisplayName("Should get project DTO")
    void shouldGetProjectDTO() {
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(true);
        when(projectBlueprintRepository.findById(projectBlueprintUuid)).thenReturn(Optional.of(projectBlueprint));
        when(projectInstanceRepository.existsById(projectInstanceId)).thenReturn(true);
        when(projectInstanceRepository.findById(projectInstanceId)).thenReturn(Optional.of(projectInstance));

        ProjectDTO result = projectService.getProjectDTO(projectBlueprintUuid, userUuid);

        assertNotNull(result);
        assertEquals(projectBlueprintUuid, result.getBlueprint().getProjectBlueprintUuid());
        assertEquals(userUuid, result.getInstance().getId().getUserUuid());
    }

    @Test
    @DisplayName("Should get all project instances as DTO with pagination")
    void shouldGetAllProjectInstancesAsDTO() {
        List<ProjectInstance> instances = Arrays.asList(projectInstance);
        Pageable pageable = PageRequest.of(0, 10);
        
        when(projectInstanceRepository.findAll(pageable)).thenReturn(new PageImpl<>(instances));
        when(projectBlueprintRepository.findById(projectBlueprintUuid)).thenReturn(Optional.of(projectBlueprint));

        List<ProjectDTO> result = projectService.getAllProjectInstancesAsDTO(10, 0);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(projectBlueprintUuid, result.get(0).getBlueprint().getProjectBlueprintUuid());
        assertEquals(userUuid, result.get(0).getInstance().getId().getUserUuid());
    }
} 