package pm.little.courseservice;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pm.little.api.models.ProjectBlueprint;
import pm.little.api.models.ProjectDaysMapper;
import pm.little.api.models.ProjectInstance;
import pm.little.api.models.dto.ProjectDTO;
import pm.little.api.models.ids.ProjectDaysMapperId;
import pm.little.api.models.ids.ProjectInstanceId;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    public ProjectBlueprint createProjectBlueprint(ProjectBlueprint blueprint);

    public List<ProjectBlueprint> getAllProjectBlueprints(int limit, int offset);

    public ProjectBlueprint getProjectBlueprint(UUID projectUuid);

    public ProjectBlueprint updateProjectBlueprint(UUID projectUuid, ProjectBlueprint updated);

    public void deleteProjectBlueprint(UUID projectUuid);

    public ProjectDaysMapper createProjectDayMapping(ProjectDaysMapper mapping);

    public ProjectDaysMapper getProjectDayMapping(UUID projectUuid, UUID dayUuid);

    public List<ProjectDaysMapper> getProjectDayMappings(UUID projectUuid, int limit, int offset);

    public ProjectDaysMapper updateProjectDayMapping(UUID projectUuid, UUID dayUuid, ProjectDaysMapper updated);

    public void deleteProjectDayMapping(UUID projectUuid, UUID dayUuid);

    public ProjectInstance createProjectInstance(ProjectInstance instance);

    public ProjectInstance getProjectInstance(UUID projectUuid, UUID userUuid);

    public List<ProjectInstance> getUserProjectInstances(UUID userUuid, int limit, int offset);

    public ProjectInstance updateProjectInstance(UUID projectUuid, UUID userUuid, ProjectInstance updated);

    public void deleteProjectInstance(UUID projectUuid, UUID userUuid);

    public ProjectDTO getProjectDTO(UUID projectUuid, UUID userUuid);

}
