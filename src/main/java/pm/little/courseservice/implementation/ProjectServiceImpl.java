package pm.little.courseservice.implementation;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pm.little.api.models.*;
import pm.little.api.models.dto.ProjectDTO;
import pm.little.api.models.enums.StatusEnum;
import pm.little.api.models.ids.ProjectDaysMapperId;
import pm.little.api.models.ids.ProjectInstanceId;
import pm.little.api.repositories.ProjectBlueprintRepository;
import pm.little.api.repositories.ProjectDaysMapperRepository;
import pm.little.api.repositories.ProjectInstanceRepository;
import pm.little.courseservice.ProjectService;
import pm.little.courseservice.exceptions.ProjectBlueprintNotFoundException;
import pm.little.courseservice.exceptions.ProjectDayNotFoundException;
import pm.little.courseservice.exceptions.ProjectInstanceNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectBlueprintRepository projectBlueprintRepository;
    private final ProjectDaysMapperRepository projectDaysMapperRepository;
    private final ProjectInstanceRepository projectInstanceRepository;

    public ProjectServiceImpl(
            ProjectBlueprintRepository projectBlueprintRepository,
            ProjectDaysMapperRepository projectDaysMapperRepository,
            ProjectInstanceRepository projectInstanceRepository
    ) {
        this.projectBlueprintRepository = projectBlueprintRepository;
        this.projectDaysMapperRepository = projectDaysMapperRepository;
        this.projectInstanceRepository = projectInstanceRepository;
    }

    // Project Blueprint Operations
    @Override
    public ProjectBlueprint createProjectBlueprint(ProjectBlueprint blueprint) {
        return projectBlueprintRepository.save(blueprint);
    }

    @Override
    public List<ProjectBlueprint> getAllProjectBlueprints(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        return projectBlueprintRepository.findAll(pageable).getContent();
    }

    @Override
    public ProjectBlueprint getProjectBlueprint(UUID projectUuid) {
        return projectBlueprintRepository.findById(projectUuid)
                .orElseThrow(() -> new ProjectBlueprintNotFoundException(projectUuid));
    }

    @Override
    public ProjectBlueprint updateProjectBlueprint(UUID projectUuid, ProjectBlueprint updated) {
        ProjectBlueprint existing = getProjectBlueprint(projectUuid);
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setDifficulty(updated.getDifficulty());
        existing.setStyle(updated.getStyle());
        existing.setPosterUrl(updated.getPosterUrl());
        existing.setWelcomeVideoUrl(updated.getWelcomeVideoUrl());
        return projectBlueprintRepository.save(existing);
    }

    @Override
    public void deleteProjectBlueprint(UUID projectUuid) {
        projectBlueprintRepository.deleteById(projectUuid);
    }

    // Project-Days Mapper Operations
    @Override
    public ProjectDaysMapper createProjectDayMapping(ProjectDaysMapper mapping) {
        return projectDaysMapperRepository.save(mapping);
    }

    @Override
    public ProjectDaysMapper getProjectDayMapping(UUID projectUuid, UUID dayUuid) {
        ProjectDaysMapperId id = new ProjectDaysMapperId(projectUuid, dayUuid);
        return projectDaysMapperRepository.findById(id)
                .orElseThrow(() -> new ProjectDayNotFoundException(id));
    }

    @Override
    public List<ProjectDaysMapper> getProjectDayMappings(UUID projectUuid, int limit, int offset) {
        Pageable pageable = PageRequest.of(offset , limit);
        return projectDaysMapperRepository.findByProjectId(projectUuid, pageable).getContent();
    }

    @Override
    public ProjectDaysMapper updateProjectDayMapping(UUID projectUuid, UUID dayUuid, ProjectDaysMapper updated) {
        ProjectDaysMapper existing = getProjectDayMapping(projectUuid, dayUuid);
        existing.setOrder(updated.getOrder());
        return projectDaysMapperRepository.save(existing);
    }

    @Override
    public void deleteProjectDayMapping(UUID projectUuid, UUID dayUuid) {
        ProjectDaysMapperId id = new ProjectDaysMapperId(projectUuid, dayUuid);
        projectDaysMapperRepository.deleteById(id);
    }

    // Project Instance Operations
    @Override
    public ProjectInstance createProjectInstance(ProjectInstance instance) {
        instance.setStatus(StatusEnum.IN_PROGRESS);
        return projectInstanceRepository.save(instance);
    }

    @Override
    public ProjectInstance getProjectInstance(UUID projectUuid, UUID userUuid) {
        ProjectInstanceId id = new ProjectInstanceId(projectUuid, userUuid);
        return projectInstanceRepository.findById(id)
                .orElseThrow(() -> new ProjectInstanceNotFoundException(id));
    }

    @Override
    public List<ProjectInstance> getUserProjectInstances(UUID userUuid, int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        return projectInstanceRepository.findByUser(userUuid, pageable).getContent();
    }

    @Override
    public ProjectInstance updateProjectInstance(UUID projectUuid, UUID userUuid, ProjectInstance updated) {
        ProjectInstance existing = getProjectInstance(projectUuid, userUuid);
        existing.setStatus(updated.getStatus());
        existing.setStartDate(updated.getStartDate());
        existing.setEndDate(updated.getEndDate());
        existing.setFeedback(updated.getFeedback());
        existing.setWhatWentWell(updated.getWhatWentWell());
        return projectInstanceRepository.save(existing);
    }

    @Override
    public void deleteProjectInstance(UUID projectUuid, UUID userUuid) {
        ProjectInstanceId id = new ProjectInstanceId(projectUuid, userUuid);
        projectInstanceRepository.deleteById(id);
    }

    // DTO Composition
    @Override
    public ProjectDTO getProjectDTO(UUID projectUuid, UUID userUuid) {
        ProjectBlueprint blueprint = getProjectBlueprint(projectUuid);
        ProjectInstance instance = getProjectInstance(projectUuid, userUuid);
        return new ProjectDTO(blueprint, instance);
    }
}