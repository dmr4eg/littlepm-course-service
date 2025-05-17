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
import pm.little.api.models.ProjectDaysMapper;
import pm.little.api.models.ProjectInstance;
import pm.little.api.models.dto.ProjectDTO;
import pm.little.api.models.enums.DifficultyEnum;
import pm.little.api.models.enums.StatusEnum;
import pm.little.api.models.enums.StyleEnum;
import pm.little.api.models.ids.ProjectDaysMapperId;
import pm.little.api.models.ids.ProjectInstanceId;
import pm.little.api.repositories.DayBlueprintRepository;
import pm.little.api.repositories.ProjectBlueprintRepository;
import pm.little.api.repositories.ProjectDaysMapperRepository;
import pm.little.api.repositories.ProjectInstanceRepository;
import pm.little.courseservice.exceptions.DayBlueprintNotFoundException;
import pm.little.courseservice.exceptions.ProjectBlueprintNotFoundException;
import pm.little.courseservice.exceptions.ProjectDayNotFoundException;
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
public class ProjectServiceImplTest {

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
    private UUID dayBlueprintUuid;
    private UUID userUuid;
    private ProjectBlueprint projectBlueprint;
    private ProjectDaysMapper projectDaysMapper;
    private ProjectInstance projectInstance;
    private ProjectDaysMapperId projectDaysMapperId;
    private ProjectInstanceId projectInstanceId;

    @BeforeEach
    void setUp() {
        projectBlueprintUuid = UUID.randomUUID();
        dayBlueprintUuid = UUID.randomUUID();
        userUuid = UUID.randomUUID();
        
        projectBlueprint = new ProjectBlueprint()
                .projectBlueprintUuid(projectBlueprintUuid)
                .title("Test Project")
                .description("Test Description")
                .difficulty(DifficultyEnum.MEDIUM)
                .style(StyleEnum.DIY)
                .posterUrl("https://example.com/poster.jpg")
                .welcomeVideoUrl("https://example.com/welcome.mp4");
                
        projectDaysMapperId = new ProjectDaysMapperId(projectBlueprintUuid, dayBlueprintUuid);
        projectDaysMapper = new ProjectDaysMapper();
        projectDaysMapper.setId(projectDaysMapperId);
        projectDaysMapper.setSortOrder(1);
        
        projectInstanceId = new ProjectInstanceId(projectBlueprintUuid, userUuid);
        projectInstance = new ProjectInstance();
        projectInstance.setId(projectInstanceId);
        projectInstance.setStatus(StatusEnum.IN_PROGRESS);
    }

    @Test
    @DisplayName("Should create project blueprint successfully")
    void shouldCreateProjectBlueprint() {
        when(projectBlueprintRepository.findById(projectBlueprintUuid)).thenReturn(Optional.empty());
        when(projectBlueprintRepository.save(any(ProjectBlueprint.class))).thenReturn(projectBlueprint);

        ProjectBlueprint result = projectService.createProjectBlueprint(projectBlueprint);

        assertNotNull(result);
        assertEquals(projectBlueprintUuid, result.getProjectBlueprintUuid());
        assertEquals("Test Project", result.getTitle());
        verify(projectBlueprintRepository, times(1)).save(any(ProjectBlueprint.class));
    }

    @Test
    @DisplayName("Should return existing project blueprint when creating with same UUID")
    void shouldReturnExistingProjectBlueprintWhenCreatingWithSameUuid() {
        when(projectBlueprintRepository.findById(projectBlueprintUuid)).thenReturn(Optional.of(projectBlueprint));

        ProjectBlueprint result = projectService.createProjectBlueprint(projectBlueprint);

        assertNotNull(result);
        assertEquals(projectBlueprintUuid, result.getProjectBlueprintUuid());
        verify(projectBlueprintRepository, never()).save(any(ProjectBlueprint.class));
    }

    @Test
    @DisplayName("Should get all project blueprints with pagination")
    void shouldGetAllProjectBlueprints() {
        List<ProjectBlueprint> projectBlueprints = Arrays.asList(projectBlueprint);
        Pageable pageable = PageRequest.of(0, 10);
        
        when(projectBlueprintRepository.findAll(pageable)).thenReturn(new PageImpl<>(projectBlueprints));

        List<ProjectBlueprint> result = projectService.getAllProjectBlueprints(10, 0);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(projectBlueprintUuid, result.get(0).getProjectBlueprintUuid());
    }

    @Test
    @DisplayName("Should get project blueprint by UUID")
    void shouldGetProjectBlueprintByUuid() {
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(true);
        when(projectBlueprintRepository.findById(projectBlueprintUuid)).thenReturn(Optional.of(projectBlueprint));

        ProjectBlueprint result = projectService.getProjectBlueprint(projectBlueprintUuid);

        assertNotNull(result);
        assertEquals(projectBlueprintUuid, result.getProjectBlueprintUuid());
    }

    @Test
    @DisplayName("Should throw exception when project blueprint not found")
    void shouldThrowExceptionWhenProjectBlueprintNotFound() {
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(false);

        assertThrows(ProjectBlueprintNotFoundException.class, () -> 
            projectService.getProjectBlueprint(projectBlueprintUuid));
    }

    @Test
    @DisplayName("Should update project blueprint successfully")
    void shouldUpdateProjectBlueprint() {
        ProjectBlueprint updatedBlueprint = new ProjectBlueprint()
                .projectBlueprintUuid(projectBlueprintUuid)
                .title("Updated Title")
                .description("Updated Description")
                .difficulty(DifficultyEnum.HARD)
                .style(StyleEnum.READY_MADE)
                .posterUrl("https://example.com/updated-poster.jpg")
                .welcomeVideoUrl("https://example.com/updated-welcome.mp4");
                
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(true);
        when(projectBlueprintRepository.findById(projectBlueprintUuid)).thenReturn(Optional.of(projectBlueprint));
        when(projectBlueprintRepository.save(any(ProjectBlueprint.class))).thenReturn(updatedBlueprint);

        ProjectBlueprint result = projectService.updateProjectBlueprint(projectBlueprintUuid, updatedBlueprint);

        assertNotNull(result);
        assertEquals(projectBlueprintUuid, result.getProjectBlueprintUuid());
        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Description", result.getDescription());
        assertEquals(DifficultyEnum.HARD, result.getDifficulty());
        assertEquals(StyleEnum.READY_MADE, result.getStyle());
        verify(projectBlueprintRepository, times(1)).save(any(ProjectBlueprint.class));
    }

    @Test
    @DisplayName("Should delete project blueprint successfully")
    void shouldDeleteProjectBlueprint() {
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(true);
        doNothing().when(projectBlueprintRepository).deleteById(projectBlueprintUuid);

        projectService.deleteProjectBlueprint(projectBlueprintUuid);

        verify(projectBlueprintRepository, times(1)).deleteById(projectBlueprintUuid);
    }

    @Test
    @DisplayName("Should create project day mapping successfully")
    void shouldCreateProjectDayMapping() {
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(true);
        when(dayBlueprintRepository.existsById(dayBlueprintUuid)).thenReturn(true);
        when(projectDaysMapperRepository.findById(projectDaysMapperId)).thenReturn(Optional.empty());
        when(projectDaysMapperRepository.save(any(ProjectDaysMapper.class))).thenReturn(projectDaysMapper);

        ProjectDaysMapper result = projectService.createProjectDayMapping(projectDaysMapper, 1);

        assertNotNull(result);
        assertEquals(projectBlueprintUuid, result.getId().getProjectBlueprintUuid());
        assertEquals(dayBlueprintUuid, result.getId().getDayBlueprintUuid());
        assertEquals(1, result.getSortOrder());
        verify(projectDaysMapperRepository, times(1)).save(any(ProjectDaysMapper.class));
    }

    @Test
    @DisplayName("Should return existing project day mapping when creating with same ID")
    void shouldReturnExistingProjectDayMappingWhenCreatingWithSameId() {
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(true);
        when(dayBlueprintRepository.existsById(dayBlueprintUuid)).thenReturn(true);
        when(projectDaysMapperRepository.findById(projectDaysMapperId)).thenReturn(Optional.of(projectDaysMapper));

        ProjectDaysMapper result = projectService.createProjectDayMapping(projectDaysMapper, 2);

        assertNotNull(result);
        assertEquals(projectBlueprintUuid, result.getId().getProjectBlueprintUuid());
        verify(projectDaysMapperRepository, never()).save(any(ProjectDaysMapper.class));
    }

    @Test
    @DisplayName("Should throw exception when creating project day mapping with non-existent project")
    void shouldThrowExceptionWhenCreatingProjectDayMappingWithNonExistentProject() {
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(false);

        assertThrows(ProjectBlueprintNotFoundException.class, () -> 
            projectService.createProjectDayMapping(projectDaysMapper, 1));
    }

    @Test
    @DisplayName("Should throw exception when creating project day mapping with non-existent day")
    void shouldThrowExceptionWhenCreatingProjectDayMappingWithNonExistentDay() {
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(true);
        when(dayBlueprintRepository.existsById(dayBlueprintUuid)).thenReturn(false);

        assertThrows(DayBlueprintNotFoundException.class, () -> 
            projectService.createProjectDayMapping(projectDaysMapper, 1));
    }

    @Test
    @DisplayName("Should get project day mapping by IDs")
    void shouldGetProjectDayMapping() {
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(true);
        when(dayBlueprintRepository.existsById(dayBlueprintUuid)).thenReturn(true);
        when(projectDaysMapperRepository.existsById(projectDaysMapperId)).thenReturn(true);
        when(projectDaysMapperRepository.findById(projectDaysMapperId)).thenReturn(Optional.of(projectDaysMapper));

        ProjectDaysMapper result = projectService.getProjectDayMapping(projectBlueprintUuid, dayBlueprintUuid);

        assertNotNull(result);
        assertEquals(projectBlueprintUuid, result.getId().getProjectBlueprintUuid());
        assertEquals(dayBlueprintUuid, result.getId().getDayBlueprintUuid());
    }

    @Test
    @DisplayName("Should throw exception when project day mapping not found")
    void shouldThrowExceptionWhenProjectDayMappingNotFound() {
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(true);
        when(dayBlueprintRepository.existsById(dayBlueprintUuid)).thenReturn(true);
        when(projectDaysMapperRepository.existsById(projectDaysMapperId)).thenReturn(false);

        assertThrows(ProjectDayNotFoundException.class, () -> 
            projectService.getProjectDayMapping(projectBlueprintUuid, dayBlueprintUuid));
    }

    @Test
    @DisplayName("Should get project day mappings with pagination")
    void shouldGetProjectDayMappings() {
        List<ProjectDaysMapper> mappings = Arrays.asList(projectDaysMapper);
        Pageable pageable = PageRequest.of(0, 10);
        
        when(projectDaysMapperRepository.findById_ProjectBlueprintUuid(projectBlueprintUuid, pageable))
            .thenReturn(new PageImpl<>(mappings));

        List<ProjectDaysMapper> result = projectService.getProjectDayMappings(projectBlueprintUuid, 10, 0);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(projectBlueprintUuid, result.get(0).getId().getProjectBlueprintUuid());
    }

    @Test
    @DisplayName("Should update project day mapping successfully")
    void shouldUpdateProjectDayMapping() {
        ProjectDaysMapper updatedMapping = new ProjectDaysMapper();
        updatedMapping.setId(projectDaysMapperId);
        updatedMapping.setSortOrder(2);
        
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(true);
        when(dayBlueprintRepository.existsById(dayBlueprintUuid)).thenReturn(true);
        when(projectDaysMapperRepository.existsById(projectDaysMapperId)).thenReturn(true);
        when(projectDaysMapperRepository.findById(projectDaysMapperId)).thenReturn(Optional.of(projectDaysMapper));
        when(projectDaysMapperRepository.save(any(ProjectDaysMapper.class))).thenReturn(updatedMapping);

        ProjectDaysMapper result = projectService.updateProjectDayMapping(projectBlueprintUuid, dayBlueprintUuid, updatedMapping);

        assertNotNull(result);
        assertEquals(2, result.getSortOrder());
        verify(projectDaysMapperRepository, times(1)).save(any(ProjectDaysMapper.class));
    }

    @Test
    @DisplayName("Should delete project day mapping successfully")
    void shouldDeleteProjectDayMapping() {
        when(projectBlueprintRepository.existsById(projectBlueprintUuid)).thenReturn(true);
        when(dayBlueprintRepository.existsById(dayBlueprintUuid)).thenReturn(true);
        when(projectDaysMapperRepository.existsById(projectDaysMapperId)).thenReturn(true);
        doNothing().when(projectDaysMapperRepository).deleteById(projectDaysMapperId);

        projectService.deleteProjectDayMapping(projectBlueprintUuid, dayBlueprintUuid);

        verify(projectDaysMapperRepository, times(1)).deleteById(projectDaysMapperId);
    }
} 