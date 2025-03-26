package pm.little.api.controllers.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import pm.little.api.controllers.ProjectsApi;
import pm.little.api.models.Project;
import pm.little.service.ProjectService;


import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("${openapi.littlePM.base-path:}")
public class ProjectsApiController implements ProjectsApi {

    private final NativeWebRequest request;
    private final ProjectService projectService;

    @Autowired
    public ProjectsApiController(NativeWebRequest request, ProjectService projectService) {
        this.request = request;
        this.projectService = projectService;
    }

    @Override
    public ResponseEntity<Project> getProject(UUID projectUUID) {
        try {
            Project project = projectService.getProject(projectUUID);
            return ResponseEntity.ok(project);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
