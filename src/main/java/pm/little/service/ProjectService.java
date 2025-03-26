package pm.little.service;

import pm.little.api.models.Project;
import pm.little.api.models.ProjectBlueprint;
import pm.little.api.models.ProjectsPostRequest;
import pm.little.api.models.ProjectUpdate;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    // Get a single project by UUID
    Project getProject(UUID projectUUID);

    // Get paginated list of blueprints
    List<ProjectBlueprint> listBlueprints(int limit, int offset);

    // Get paginated list of projects with optional status filter
    List<Project> listProjects(int limit, int offset, String status);

    // Create a new project from blueprint
    Project createProject(ProjectsPostRequest request);

    // Update an existing project
    Project updateProject(UUID projectUUID, ProjectUpdate projectUpdate);
}
