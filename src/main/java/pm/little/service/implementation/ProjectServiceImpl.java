package pm.little.service.implementation;

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
import pm.little.api.repositories.ProjectRepository;
import pm.little.service.ProjectService;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {
    ProjectBlueprintRepository projectBlueprintRepository;
    ProjectRepository projectRepository;

    @Override
    public Project getProject(UUID projectUUID) {
        return projectRepository.findById(projectUUID)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with UUID: " + projectUUID));
    }

    @Override
    public List<ProjectBlueprint> listBlueprints(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        return projectBlueprintRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Project> listProjects(int limit, int offset, String status) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        if (status != null && !status.isEmpty()) {
            return projectRepository.findByStatus(status, pageable);
        }
        return projectRepository.findAll(pageable).getContent();
    }

    @Override
    public Project createProject(ProjectsPostRequest request) {
        ProjectBlueprint blueprint = projectBlueprintRepository.findById(request.getBlueprintUUID())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Blueprint not found with UUID: " + request.getBlueprintUUID()));

        Project project = new Project();
        project.setName(request.getName());
//        project.setDescription(request.getDescription());
//        project.setOwnerUUID(request.getOwnerUUID());
//        project.setStatus(StatusEnum.IN_PROGRESS);
//        project.setBlueprintUUID(blueprint.getBlueprintUUID());
//
//        project.setDefaultParameters(blueprint.getDefaultSettings());

        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(UUID projectUUID, ProjectUpdate projectUpdate) {
        Project existingProject = getProject(projectUUID);

        if (projectUpdate.getName() != null) {
            existingProject.setName(projectUpdate.getName());
        }
//        if (projectUpdate.getDescription() != null) {
//            existingProject.setDescription(projectUpdate.getDescription());
//        }
        if (projectUpdate.getStatus() != null) {
            existingProject.setStatus(projectUpdate.getStatus());
        }

        return projectRepository.save(existingProject);
    }
}
