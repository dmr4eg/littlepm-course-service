package pm.little.api.controllers.implementation;

import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import pm.little.api.controllers.ProjectDaysMapperApi;
import pm.little.api.models.ProjectDaysMapper;
import pm.little.courseservice.ProjectService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen",
        date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]",
        comments = "Generator version: 7.11.0")
@Controller
@RequestMapping("${openapi.projectDay.base-path:}")
public class ProjectDaysMapperApiController implements ProjectDaysMapperApi {

    private final NativeWebRequest request;
    private final ProjectService projectService;

    @Autowired
    public ProjectDaysMapperApiController(NativeWebRequest request, ProjectService projectService) {
        this.request = request;
        this.projectService = projectService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    /**
     * GET /project-days-mapper?limit=...&offset=...
     *
     * If you need to list *all* project-day mappings across *all* projects,
     * you either need a new service method (e.g. getAllProjectDayMappings)
     * or call the repository directly. Here we assume you added the method:
     *   List<ProjectDaysMapper> getAllProjectDayMappings(int limit, int offset);
     * in your ProjectService.
     */
    @Override
    public ResponseEntity<List<ProjectDaysMapper>> projectDaysMapperGet(Integer limit, Integer offset) {
        // Example if your service supports it:
        // List<ProjectDaysMapper> list = projectService.getAllProjectDayMappings(limit, offset);
        // return ResponseEntity.ok(list);

        // If not implemented, you can return 501 or something:
        // return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

        // For demonstration, assume service method is there:
        List<ProjectDaysMapper> list = projectService.getAllProjectDayMappings(limit, offset);
        return ResponseEntity.ok(list);
    }

    /**
     * POST /project-days-mapper
     */
    @Override
    public ResponseEntity<ProjectDaysMapper> projectDaysMapperPost(ProjectDaysMapper projectDaysMapper) {
        // The 'order' field must come from the ProjectDaysMapper object's 'order'
        ProjectDaysMapper created = projectService.createProjectDayMapping(projectDaysMapper, projectDaysMapper.getSortOrder());
        return ResponseEntity.ok(created);
    }

    /**
     * DELETE /project-days-mapper/{project_blueprint_uuid}/{day_blueprint_uuid}
     */
    @Override
    public ResponseEntity<Void> projectDaysMapperProjectBlueprintUuidDayBlueprintUuidDelete(
            UUID projectBlueprintUuid,
            UUID dayBlueprintUuid
    ) {
        projectService.deleteProjectDayMapping(projectBlueprintUuid, dayBlueprintUuid);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /project-days-mapper/{project_blueprint_uuid}/{day_blueprint_uuid}
     */
    @Override
    public ResponseEntity<ProjectDaysMapper> projectDaysMapperProjectBlueprintUuidDayBlueprintUuidGet(
            UUID projectBlueprintUuid,
            UUID dayBlueprintUuid
    ) {
        ProjectDaysMapper mapping = projectService.getProjectDayMapping(projectBlueprintUuid, dayBlueprintUuid);
        return ResponseEntity.ok(mapping);
    }

    /**
     * PUT /project-days-mapper/{project_blueprint_uuid}/{day_blueprint_uuid}
     */
    @Override
    public ResponseEntity<ProjectDaysMapper> projectDaysMapperProjectBlueprintUuidDayBlueprintUuidPut(
            UUID projectBlueprintUuid,
            UUID dayBlueprintUuid,
            ProjectDaysMapper projectDaysMapper
    ) {
        ProjectDaysMapper updated = projectService.updateProjectDayMapping(projectBlueprintUuid, dayBlueprintUuid, projectDaysMapper);
        return ResponseEntity.ok(updated);
    }
}
