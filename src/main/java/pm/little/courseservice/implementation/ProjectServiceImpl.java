package pm.little.courseservice.implementation;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pm.little.api.models.Project;
import pm.little.api.models.ProjectBlueprint;
import pm.little.api.models.ProjectUpdate;
import pm.little.api.models.ProjectsPostRequest;
import pm.little.api.models.enums.StatusEnum;
import pm.little.api.repositories.ProjectBlueprintRepository;
import pm.little.courseservice.ProjectService;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {
    ProjectBlueprintRepository projectBlueprintRepository;

    public ProjectServiceImpl(ProjectBlueprintRepository projectBlueprintRepository) {
        this.projectBlueprintRepository = projectBlueprintRepository;
    }

    @Override
    public ProjectBlueprint createProject(ProjectsPostRequest projectsPostRequest) {
        ProjectBlueprint projectBlueprint = projectBlueprintRepository.findById(projectsPostRequest.getProjectBlueprintId())
                .orElseThrow(() -> new ResourceNotFoundException("ProjectBlueprint not found with id " + projectsPostRequest.getProjectBlueprintId()));
        Project project = new Project();
        project.setProjectBlueprint(projectBlueprint);
        project.setStatus(StatusEnum.IN_PROGRESS

        );
        return project;

    }
}
