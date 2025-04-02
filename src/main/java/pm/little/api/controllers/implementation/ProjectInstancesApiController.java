package pm.little.api.controllers.implementation;

import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import pm.little.api.controllers.ProjectInstancesApi;
import pm.little.api.models.ProjectInstance;
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
     *
     * The OpenAPI spec suggests returning "all" project instances for admin or user.
     * If you truly want "all" for an admin, you can add a method to your service:
     *   List<ProjectInstance> getAllProjectInstances(int limit, int offset);
     *
     * Alternatively, you might:
     * - Check if the current user is admin. If so, getAll.
     * - Otherwise, get only the instances for that user.
     *
     * Below is a simple example returning all:
     */
    @Override
    public ResponseEntity<List<ProjectInstance>> projectInstancesGet() {
        // e.g. you might want to parse limit/offset from headers or add them to the interface
        // For demonstration, assume you've got service method for all instances:
        List<ProjectInstance> instances = projectService.getAllProjectInstances(100, 0); // Hard-coded example
        return ResponseEntity.ok(instances);
    }

    /**
     * POST /project-instances
     */
    @Override
    public ResponseEntity<ProjectInstance> projectInstancesPost(ProjectInstance projectInstance) {
        ProjectInstance created = projectService.createProjectInstance(projectInstance);
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
     */
    @Override
    public ResponseEntity<ProjectInstance> projectInstancesProjectBlueprintUuidUserUuidGet(
            UUID projectBlueprintUuid,
            UUID userUuid
    ) {
        ProjectInstance instance = projectService.getProjectInstance(projectBlueprintUuid, userUuid);
        return ResponseEntity.ok(instance);
    }

    /**
     * PUT /project-instances/{project_blueprint_uuid}/{user_uuid}
     */
    @Override
    public ResponseEntity<ProjectInstance> projectInstancesProjectBlueprintUuidUserUuidPut(
            UUID projectBlueprintUuid,
            UUID userUuid,
            ProjectInstance projectInstance
    ) {
        ProjectInstance updated = projectService.updateProjectInstance(projectBlueprintUuid, userUuid, projectInstance);
        return ResponseEntity.ok(updated);
    }
}
