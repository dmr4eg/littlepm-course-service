package pm.little.courseservice.implementation;

import org.springframework.stereotype.Service;
//import pm.little.api.models.Project;
//import pm.little.api.models.ProjectUpdate;
//import pm.little.api.models.ProjectsPostRequest;
import pm.little.api.repositories.ProjectBlueprintRepository;
import pm.little.courseservice.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
    ProjectBlueprintRepository projectBlueprintRepository;

    public ProjectServiceImpl(ProjectBlueprintRepository projectBlueprintRepository) {
        this.projectBlueprintRepository = projectBlueprintRepository;
    }

    //public Project createProject(ProjectsPostRequest projectsPostRequest) {
    //    return null;
}
