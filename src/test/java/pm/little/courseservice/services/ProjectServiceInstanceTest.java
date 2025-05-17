package pm.little.courseservice.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
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
import static org.mockito.ArgumentMatchers.eq;
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

    private ProjectBlueprint projectBlueprint;
    private ProjectInstance projectInstance;
    private ProjectInstanceId projectInstanceId;
    private UUID testUuid;
    private UUID userUuid;

    @BeforeEach
    void setUp() {
        testUuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        userUuid = UUID.fromString("00000000-0000-0000-0000-000000000002");
        
        projectBlueprint = new ProjectBlueprint()
                .projectBlueprintUuid(testUuid)
                .title("Test Project")
                .description("Test Description")
                .difficulty(DifficultyEnum.MEDIUM)
                .style(StyleEnum.DIY)
                .posterUrl("https://example.com/poster.jpg")
                .welcomeVideoUrl("https://example.com/welcome.mp4");
        
        projectInstanceId = new ProjectInstanceId(testUuid, userUuid);
        projectInstance = new ProjectInstance();
        projectInstance.setId(projectInstanceId);
        projectInstance.setStatus(StatusEnum.IN_PROGRESS);
    }

    @Test
    @DisplayName("Should create project instance successfully")
    void shouldCreateProjectInstance() {
        // Setup
        when(projectBlueprintRepository.existsById(any(UUID.class))).thenReturn(true);
        when(projectBlueprintRepository.findById(any(UUID.class))).thenReturn(Optional.of(projectBlueprint));
        when(projectInstanceRepository.findById(any(ProjectInstanceId.class))).thenReturn(Optional.empty());
        when(projectInstanceRepository.save(any(ProjectInstance.class))).thenReturn(projectInstance);

        // Execute
        ProjectDTO result = projectService.createProjectInstance(projectInstance);

        // Verify
        assertNotNull(result);
        assertEquals(testUuid, result.getBlueprint().getProjectBlueprintUuid());
        assertEquals(testUuid, result.getInstance().getId().getProjectBlueprintUuid());
        assertEquals(userUuid, result.getInstance().getId().getUserUuid());
        assertEquals(StatusEnum.IN_PROGRESS, result.getInstance().getStatus());
        verify(projectInstanceRepository, times(1)).save(any(ProjectInstance.class));
    }

    @Test
    @DisplayName("Should throw exception when creating project instance with non-existent blueprint")
    void shouldThrowExceptionWhenCreatingProjectInstanceWithNonExistentBlueprint() {
        // Setup
        when(projectBlueprintRepository.existsById(any(UUID.class))).thenReturn(false);

        // Execute & Verify
        assertThrows(ProjectBlueprintNotFoundException.class, () -> 
            projectService.createProjectInstance(projectInstance));
    }

    @Test
    @DisplayName("Should update project instance successfully")
    void shouldUpdateProjectInstance() {
        // Setup
        ProjectInstance updatedInstance = new ProjectInstance();
        updatedInstance.setId(projectInstanceId);
        updatedInstance.setStatus(StatusEnum.COMPLETED);
        
        when(projectBlueprintRepository.existsById(any(UUID.class))).thenReturn(true);
        when(projectBlueprintRepository.findById(any(UUID.class))).thenReturn(Optional.of(projectBlueprint));
        when(projectInstanceRepository.existsById(any(ProjectInstanceId.class))).thenReturn(true);
        when(projectInstanceRepository.findById(any(ProjectInstanceId.class))).thenReturn(Optional.of(projectInstance));
        when(projectInstanceRepository.save(any(ProjectInstance.class))).thenReturn(updatedInstance);

        // Execute
        ProjectDTO result = projectService.updateProjectInstance(testUuid, userUuid, updatedInstance);

        // Verify
        assertNotNull(result);
        assertEquals(StatusEnum.COMPLETED, result.getInstance().getStatus());
        verify(projectInstanceRepository, times(1)).save(any(ProjectInstance.class));
    }

    @Test
    @DisplayName("Should delete project instance successfully")
    void shouldDeleteProjectInstance() {
        // Setup
        when(projectBlueprintRepository.existsById(any(UUID.class))).thenReturn(true);
        when(projectInstanceRepository.existsById(any(ProjectInstanceId.class))).thenReturn(true);
        doNothing().when(projectInstanceRepository).deleteById(any(ProjectInstanceId.class));

        // Execute
        projectService.deleteProjectInstance(testUuid, userUuid);

        // Verify
        verify(projectInstanceRepository, times(1)).deleteById(any(ProjectInstanceId.class));
    }

    @Test
    @DisplayName("Should get project instance by IDs")
    void shouldGetProjectInstance() {
        // Setup
        when(projectBlueprintRepository.existsById(any(UUID.class))).thenReturn(true);
        when(projectBlueprintRepository.findById(any(UUID.class))).thenReturn(Optional.of(projectBlueprint));
        when(projectInstanceRepository.findById(any(ProjectInstanceId.class))).thenReturn(Optional.of(projectInstance));

        // Execute
        ProjectDTO result = projectService.getProjectInstance(testUuid, userUuid);

        // Verify
        assertNotNull(result);
        assertEquals(testUuid, result.getBlueprint().getProjectBlueprintUuid());
        assertEquals(userUuid, result.getInstance().getId().getUserUuid());
    }

    @Test
    @DisplayName("Should throw exception when project instance not found")
    void shouldThrowExceptionWhenProjectInstanceNotFound() {
        // Setup
        when(projectBlueprintRepository.existsById(any(UUID.class))).thenReturn(true);
        when(projectInstanceRepository.findById(any(ProjectInstanceId.class))).thenReturn(Optional.empty());

        // Execute & Verify
        assertThrows(ProjectInstanceNotFoundException.class, () -> 
            projectService.getProjectInstance(testUuid, userUuid));
    }

    @Test
    @DisplayName("Should get user project instances as DTO")
    void shouldGetUserProjectInstancesAsDTO() {
        // Setup
        List<ProjectInstance> instances = Arrays.asList(projectInstance);
        
        when(projectInstanceRepository.findById_UserUuid(any(UUID.class), any(Pageable.class))).thenReturn(new PageImpl<>(instances));
        when(projectBlueprintRepository.existsById(any(UUID.class))).thenReturn(true);
        when(projectBlueprintRepository.findById(any(UUID.class))).thenReturn(Optional.of(projectBlueprint));

        // Execute
        List<ProjectDTO> result = projectService.getUserProjectInstancesAsDTO(userUuid, 10, 0);

        // Verify
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testUuid, result.get(0).getBlueprint().getProjectBlueprintUuid());
        assertEquals(userUuid, result.get(0).getInstance().getId().getUserUuid());
    }

    @Test
    @DisplayName("Should get project DTO")
    void shouldGetProjectDTO() {
        // Setup
        when(projectBlueprintRepository.existsById(any(UUID.class))).thenReturn(true);
        when(projectBlueprintRepository.findById(any(UUID.class))).thenReturn(Optional.of(projectBlueprint));
        when(projectInstanceRepository.findById(any(ProjectInstanceId.class))).thenReturn(Optional.of(projectInstance));

        // Execute
        ProjectDTO result = projectService.getProjectDTO(testUuid, userUuid);

        // Verify
        assertNotNull(result);
        assertEquals(testUuid, result.getBlueprint().getProjectBlueprintUuid());
        assertEquals(userUuid, result.getInstance().getId().getUserUuid());
    }

    @Test
    @DisplayName("Should get all project instances as DTO")
    void shouldGetAllProjectInstancesAsDTO() {
        // Setup
        List<ProjectInstance> instances = Arrays.asList(projectInstance);
        
        when(projectInstanceRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(instances));
        when(projectBlueprintRepository.existsById(any(UUID.class))).thenReturn(true);
        when(projectBlueprintRepository.findById(any(UUID.class))).thenReturn(Optional.of(projectBlueprint));

        // Execute
        List<ProjectDTO> result = projectService.getAllProjectInstancesAsDTO(10, 0);

        // Verify
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testUuid, result.get(0).getBlueprint().getProjectBlueprintUuid());
        assertEquals(userUuid, result.get(0).getInstance().getId().getUserUuid());
    }
} 