package pm.little.api.controllers.implementation;

import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import pm.little.api.controllers.ProjectsApi;
import pm.little.api.models.ProjectBlueprint;
import pm.little.courseservice.ProjectService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen",
        date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]",
        comments = "Generator version: 7.11.0")
@Controller
@RequestMapping("${openapi.projectDay.base-path:}")
public class ProjectsApiController implements ProjectsApi {

    private final NativeWebRequest request;
    private final ProjectService projectService;

    @Autowired
    public ProjectsApiController(NativeWebRequest request, ProjectService projectService) {
        this.request = request;
        this.projectService = projectService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    /**
     * GET /projects?limit=...&offset=...
     */
    @Override
    public ResponseEntity<List<ProjectBlueprint>> projectsGet(Integer limit, Integer offset) {
        if (limit == null || offset == null) {
            return ResponseEntity.badRequest().build();
        }
        List<ProjectBlueprint> blueprints = projectService.getAllProjectBlueprints(limit, offset);
        return ResponseEntity.ok(blueprints);
    }

    /**
     * POST /projects
     */
    @Override
    public ResponseEntity<ProjectBlueprint> projectsPost(ProjectBlueprint projectBlueprint) {
        if (projectBlueprint == null || projectBlueprint.getProjectBlueprintUuid() == null) {
            return ResponseEntity.badRequest().build();
        }
        ProjectBlueprint created = projectService.createProjectBlueprint(projectBlueprint);
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(created);
    }

    /**
     * DELETE /projects/{project_blueprint_uuid}
     */
    @Override
    public ResponseEntity<Void> projectsProjectBlueprintUuidDelete(UUID projectBlueprintUuid) {
        if (projectBlueprintUuid == null) {
            return ResponseEntity.badRequest().build();
        }
        projectService.deleteProjectBlueprint(projectBlueprintUuid);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /projects/{project_blueprint_uuid}
     */
    @Override
    public ResponseEntity<ProjectBlueprint> projectsProjectBlueprintUuidGet(UUID projectBlueprintUuid) {
        if (projectBlueprintUuid == null) {
            return ResponseEntity.badRequest().build();
        }
        ProjectBlueprint blueprint = projectService.getProjectBlueprint(projectBlueprintUuid);
        return ResponseEntity.ok(blueprint);
    }

    /**
     * PUT /projects/{project_blueprint_uuid}
     */
    @Override
    public ResponseEntity<ProjectBlueprint> projectsProjectBlueprintUuidPut(
            UUID projectBlueprintUuid,
            ProjectBlueprint projectBlueprint
    ) {
        if (projectBlueprintUuid == null || projectBlueprint == null) {
            return ResponseEntity.badRequest().build();
        }
        ProjectBlueprint updated = projectService.updateProjectBlueprint(projectBlueprintUuid, projectBlueprint);
        return ResponseEntity.ok(updated);
    }
}
