package pm.little.api.controllers.implementation;

import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import pm.little.api.controllers.ProjectInstancesApi;
import pm.little.api.models.ProjectInstance;
import pm.little.api.models.dto.ProjectDTO;
import pm.little.courseservice.ProjectService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen",
        date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]",
        comments = "Generator version: 7.11.0")
@Controller
@RequestMapping("${openapi.projectDay.base-path:}")
public class ProjectInstancesApiController implements ProjectInstancesApi {

    private final NativeWebRequest request;
    private final ProjectService projectService;

    @Autowired
    public ProjectInstancesApiController(NativeWebRequest request, ProjectService projectService) {
        this.request = request;
        this.projectService = projectService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    /**
     * GET /project-instances
     * Return a list of ProjectDTO instead of ProjectInstance.
     */
    @Override
    public ResponseEntity<List<ProjectDTO>> projectInstancesGet(Integer limit, Integer offset, UUID userUuid) {
        // For example, if you want to return *all* project instances:
        //   List<ProjectDTO> dtos = projectService.getAllProjectInstancesAsDTO(limit, offset);
        // Or if you want to return only the user’s project instances:
        //   List<ProjectDTO> dtos = projectService.getUserProjectInstancesAsDTO(userUuid, limit, offset);
        //
        // For now, suppose we return all (or you can adjust):
        List<ProjectDTO> dtos = projectService.getAllProjectInstancesAsDTO(limit, offset);
        return ResponseEntity.ok(dtos);
    }

    /**
     * POST /project-instances
     * Create a new project instance, returning ProjectDTO
     */
    @Override
    public ResponseEntity<ProjectDTO> projectInstancesPost(ProjectInstance projectInstance) {
        ProjectDTO created = projectService.createProjectInstance(projectInstance);
        return ResponseEntity.ok(created);
    }

    /**
     * DELETE /project-instances/{project_blueprint_uuid}/{user_uuid}
     */
    @Override
    public ResponseEntity<Void> projectInstancesProjectBlueprintUuidUserUuidDelete(
            UUID projectBlueprintUuid,
            UUID userUuid
    ) {
        projectService.deleteProjectInstance(projectBlueprintUuid, userUuid);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /project-instances/{project_blueprint_uuid}/{user_uuid}
     * Return a single ProjectDTO
     */
    @Override
    public ResponseEntity<ProjectDTO> projectInstancesProjectBlueprintUuidUserUuidGet(
            UUID projectBlueprintUuid,
            UUID userUuid
    ) {
        ProjectDTO dto = projectService.getProjectInstance(projectBlueprintUuid, userUuid);
        return ResponseEntity.ok(dto);
    }

    /**
     * PUT /project-instances/{project_blueprint_uuid}/{user_uuid}
     * Update an existing project instance, returning ProjectDTO
     */
    @Override
    public ResponseEntity<ProjectDTO> projectInstancesProjectBlueprintUuidUserUuidPut(
            UUID projectBlueprintUuid,
            UUID userUuid,
            ProjectInstance projectInstance
    ) {
        ProjectDTO updated = projectService.updateProjectInstance(projectBlueprintUuid, userUuid, projectInstance);
        return ResponseEntity.ok(updated);
    }
}
