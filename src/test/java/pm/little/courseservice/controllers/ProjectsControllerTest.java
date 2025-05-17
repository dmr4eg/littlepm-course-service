package pm.little.courseservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.NativeWebRequest;
import pm.little.api.controllers.implementation.ProjectsApiController;
import pm.little.api.models.ProjectBlueprint;
import pm.little.api.models.enums.DifficultyEnum;
import pm.little.api.models.enums.StyleEnum;
import pm.little.courseservice.ProjectService;
import pm.little.courseservice.TestConfig;
import pm.little.courseservice.exceptions.ProjectBlueprintNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectsApiController.class)
@Import(TestConfig.class)
public class ProjectsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProjectService projectService;

    @MockBean
    private NativeWebRequest nativeWebRequest;

    private UUID projectBlueprintUuid;
    private ProjectBlueprint projectBlueprint;

    @BeforeEach
    void setUp() {
        projectBlueprintUuid = UUID.randomUUID();
        
        projectBlueprint = new ProjectBlueprint()
                .projectBlueprintUuid(projectBlueprintUuid)
                .title("Test Project")
                .description("Test Description")
                .difficulty(DifficultyEnum.MEDIUM)
                .style(StyleEnum.DIY)
                .posterUrl("https://example.com/poster.jpg")
                .welcomeVideoUrl("https://example.com/welcome.mp4");
    }

    @Test
    @DisplayName("Should get all project blueprints with pagination")
    void shouldGetAllProjectBlueprintsWithPagination() throws Exception {
        List<ProjectBlueprint> projectBlueprints = Arrays.asList(projectBlueprint);
        when(projectService.getAllProjectBlueprints(eq(10), eq(0))).thenReturn(projectBlueprints);

        mockMvc.perform(get("/projects")
                .param("limit", "10")
                .param("offset", "0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].project_blueprint_uuid", is(projectBlueprintUuid.toString())))
                .andExpect(jsonPath("$[0].title", is("Test Project")))
                .andExpect(jsonPath("$[0].description", is("Test Description")));

        verify(projectService, times(1)).getAllProjectBlueprints(10, 0);
    }

    @Test
    @DisplayName("Should return bad request when missing pagination parameters")
    void shouldReturnBadRequestWhenMissingPaginationParameters() throws Exception {
        mockMvc.perform(get("/projects")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(projectService, never()).getAllProjectBlueprints(any(Integer.class), any(Integer.class));
    }

    @Test
    @DisplayName("Should create project blueprint")
    void shouldCreateProjectBlueprint() throws Exception {
        when(projectService.createProjectBlueprint(any(ProjectBlueprint.class))).thenReturn(projectBlueprint);

        mockMvc.perform(post("/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projectBlueprint)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.project_blueprint_uuid", is(projectBlueprintUuid.toString())))
                .andExpect(jsonPath("$.title", is("Test Project")))
                .andExpect(jsonPath("$.description", is("Test Description")));

        verify(projectService, times(1)).createProjectBlueprint(any(ProjectBlueprint.class));
    }

    @Test
    @DisplayName("Should return bad request when creating project blueprint with null UUID")
    void shouldReturnBadRequestWhenCreatingProjectBlueprintWithNullUuid() throws Exception {
        ProjectBlueprint invalidBlueprint = new ProjectBlueprint();
        invalidBlueprint.setTitle("Invalid Project");

        mockMvc.perform(post("/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidBlueprint)))
                .andExpect(status().isBadRequest());

        verify(projectService, never()).createProjectBlueprint(any(ProjectBlueprint.class));
    }

    @Test
    @DisplayName("Should get project blueprint by UUID")
    void shouldGetProjectBlueprintByUuid() throws Exception {
        when(projectService.getProjectBlueprint(eq(projectBlueprintUuid))).thenReturn(projectBlueprint);

        mockMvc.perform(get("/projects/{project_blueprint_uuid}", projectBlueprintUuid)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.project_blueprint_uuid", is(projectBlueprintUuid.toString())))
                .andExpect(jsonPath("$.title", is("Test Project")))
                .andExpect(jsonPath("$.description", is("Test Description")));

        verify(projectService, times(1)).getProjectBlueprint(projectBlueprintUuid);
    }

    @Test
    @DisplayName("Should return not found when project blueprint does not exist")
    void shouldReturnNotFoundWhenProjectBlueprintDoesNotExist() throws Exception {
        when(projectService.getProjectBlueprint(eq(projectBlueprintUuid)))
                .thenThrow(new ProjectBlueprintNotFoundException(projectBlueprintUuid));

        mockMvc.perform(get("/projects/{project_blueprint_uuid}", projectBlueprintUuid)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(projectService, times(1)).getProjectBlueprint(projectBlueprintUuid);
    }

    @Test
    @DisplayName("Should update project blueprint")
    void shouldUpdateProjectBlueprint() throws Exception {
        ProjectBlueprint updatedBlueprint = new ProjectBlueprint()
                .projectBlueprintUuid(projectBlueprintUuid)
                .title("Updated Title")
                .description("Updated Description")
                .difficulty(DifficultyEnum.HARD)
                .style(StyleEnum.READY_MADE)
                .posterUrl("https://example.com/updated-poster.jpg")
                .welcomeVideoUrl("https://example.com/updated-welcome.mp4");

        when(projectService.updateProjectBlueprint(eq(projectBlueprintUuid), any(ProjectBlueprint.class)))
                .thenReturn(updatedBlueprint);

        mockMvc.perform(put("/projects/{project_blueprint_uuid}", projectBlueprintUuid)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBlueprint)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.project_blueprint_uuid", is(projectBlueprintUuid.toString())))
                .andExpect(jsonPath("$.title", is("Updated Title")))
                .andExpect(jsonPath("$.description", is("Updated Description")));

        verify(projectService, times(1)).updateProjectBlueprint(eq(projectBlueprintUuid), any(ProjectBlueprint.class));
    }

    @Test
    @DisplayName("Should delete project blueprint")
    void shouldDeleteProjectBlueprint() throws Exception {
        doNothing().when(projectService).deleteProjectBlueprint(eq(projectBlueprintUuid));

        mockMvc.perform(delete("/projects/{project_blueprint_uuid}", projectBlueprintUuid)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(projectService, times(1)).deleteProjectBlueprint(projectBlueprintUuid);
    }
} 