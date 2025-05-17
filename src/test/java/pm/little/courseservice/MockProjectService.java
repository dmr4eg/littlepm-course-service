package pm.little.courseservice;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pm.little.api.models.ProjectBlueprint;
import pm.little.api.models.ProjectDaysMapper;
import pm.little.api.models.ProjectInstance;
import pm.little.api.models.dto.ProjectDTO;
import pm.little.api.models.ids.ProjectDaysMapperId;
import pm.little.api.models.ids.ProjectInstanceId;

import java.util.*;

@Service
@Profile("test")
public class MockProjectService implements ProjectService {

    private final Map<UUID, ProjectBlueprint> projectBlueprints = new HashMap<>();
    private final Map<String, ProjectDaysMapper> projectDaysMappings = new HashMap<>();
    private final Map<String, ProjectInstance> projectInstances = new HashMap<>();

    @Override
    public ProjectBlueprint createProjectBlueprint(ProjectBlueprint blueprint) {
        projectBlueprints.put(blueprint.getProjectBlueprintUuid(), blueprint);
        return blueprint;
    }

    @Override
    public List<ProjectBlueprint> getAllProjectBlueprints(int limit, int offset) {
        return new ArrayList<>(projectBlueprints.values())
                .stream()
                .skip(offset)
                .limit(limit)
                .toList();
    }

    @Override
    public ProjectBlueprint getProjectBlueprint(UUID projectUuid) {
        return projectBlueprints.get(projectUuid);
    }

    @Override
    public ProjectBlueprint updateProjectBlueprint(UUID projectUuid, ProjectBlueprint updated) {
        projectBlueprints.put(projectUuid, updated);
        return updated;
    }

    @Override
    public void deleteProjectBlueprint(UUID projectUuid) {
        projectBlueprints.remove(projectUuid);
    }

    @Override
    public ProjectDaysMapper createProjectDayMapping(ProjectDaysMapper mapping, int sortOrder) {
        String key = mapping.getId().getProjectBlueprintUuid() + "-" + mapping.getId().getDayBlueprintUuid();
        projectDaysMappings.put(key, mapping);
        return mapping;
    }

    @Override
    public ProjectDaysMapper getProjectDayMapping(UUID projectUuid, UUID dayUuid) {
        String key = projectUuid + "-" + dayUuid;
        return projectDaysMappings.get(key);
    }

    @Override
    public int getProjectDayMappingsOrder(UUID projectUuid, UUID dayUuid) {
        return 0;
    }

    @Override
    public List<ProjectDaysMapper> getProjectDayMappings(UUID projectUuid, int limit, int offset) {
        return projectDaysMappings.values().stream()
                .filter(m -> m.getId().getProjectBlueprintUuid().equals(projectUuid))
                .skip(offset)
                .limit(limit)
                .toList();
    }

    @Override
    public ProjectDaysMapper updateProjectDayMapping(UUID projectUuid, UUID dayUuid, ProjectDaysMapper updated) {
        String key = projectUuid + "-" + dayUuid;
        projectDaysMappings.put(key, updated);
        return updated;
    }

    @Override
    public void deleteProjectDayMapping(UUID projectUuid, UUID dayUuid) {
        String key = projectUuid + "-" + dayUuid;
        projectDaysMappings.remove(key);
    }

    @Override
    public ProjectDTO createProjectInstance(ProjectInstance instance) {
        String key = instance.getId().getProjectBlueprintUuid() + "-" + instance.getId().getUserUuid();
        projectInstances.put(key, instance);
        return new ProjectDTO(); // Simplified for testing
    }

    @Override
    public ProjectDTO getProjectInstance(UUID projectUuid, UUID userUuid) {
        return new ProjectDTO(); // Simplified for testing
    }

    @Override
    public List<ProjectDTO> getUserProjectInstancesAsDTO(UUID userUuid, int limit, int offset) {
        return List.of(); // Simplified for testing
    }

    @Override
    public ProjectDTO updateProjectInstance(UUID projectUuid, UUID userUuid, ProjectInstance updated) {
        String key = projectUuid + "-" + userUuid;
        projectInstances.put(key, updated);
        return new ProjectDTO(); // Simplified for testing
    }

    @Override
    public void deleteProjectInstance(UUID projectUuid, UUID userUuid) {
        String key = projectUuid + "-" + userUuid;
        projectInstances.remove(key);
    }

    @Override
    public ProjectDTO getProjectDTO(UUID projectUuid, UUID userUuid) {
        return new ProjectDTO(); // Simplified for testing
    }

    @Override
    public List<ProjectDTO> getAllProjectInstancesAsDTO(int limit, int offset) {
        return List.of(); // Simplified for testing
    }

    @Override
    public List<ProjectDaysMapper> getAllProjectDayMappings(int limit, int offset) {
        return new ArrayList<>(projectDaysMappings.values())
                .stream()
                .skip(offset)
                .limit(limit)
                .toList();
    }
} 