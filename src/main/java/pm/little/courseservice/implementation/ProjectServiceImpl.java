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
        ProjectBlueprint existing = projectBlueprintRepository.findById(blueprint.getProjectBlueprintUuid()).orElse(null);
        if (existing != null) {
            return existing;
        }
        return projectBlueprintRepository.save(blueprint);
    }

    @Override
    public List<ProjectBlueprint> getAllProjectBlueprints(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        return projectBlueprintRepository.findAll(pageable).getContent();
    }

    @Override
    public ProjectBlueprint getProjectBlueprint(UUID projectUuid) {
        if (!projectBlueprintRepository.existsById(projectUuid)) {
            throw new ProjectBlueprintNotFoundException(projectUuid);
        }
        return projectBlueprintRepository.findById(projectUuid)
                .orElseThrow(() -> new ProjectBlueprintNotFoundException(projectUuid));
    }

    @Override
    public ProjectBlueprint updateProjectBlueprint(UUID projectUuid, ProjectBlueprint updated) {
        if (!projectBlueprintRepository.existsById(projectUuid)) {
            throw new ProjectBlueprintNotFoundException(projectUuid);
        }
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
        if (!projectBlueprintRepository.existsById(projectUuid)) {
            throw new ProjectBlueprintNotFoundException(projectUuid);
        }
        projectBlueprintRepository.deleteById(projectUuid);
    }

    // Project Days Mapper Operations
    @Override
    public ProjectDaysMapper createProjectDayMapping(ProjectDaysMapper mapping, int sortOrder) {
        ProjectDaysMapper existing = projectDaysMapperRepository.findById(mapping.getId()).orElse(null);
        if (existing != null) {
            return existing;
        }
        mapping.setSortOrder(sortOrder);
        return projectDaysMapperRepository.save(mapping);
    }

    @Override
    public ProjectDaysMapper getProjectDayMapping(UUID projectUuid, UUID dayUuid) {
        ProjectDaysMapperId id = new ProjectDaysMapperId(projectUuid, dayUuid);
        if (!projectDaysMapperRepository.existsById(id)) {
            throw new ProjectDayNotFoundException(id);
        }
        return projectDaysMapperRepository.findById(id)
                .orElseThrow(() -> new ProjectDayNotFoundException(id));
    }

    @Override
    public List<ProjectDaysMapper> getProjectDayMappings(UUID projectUuid, int limit, int offset) {
        Pageable pageable = PageRequest.of(offset , limit);
        return projectDaysMapperRepository.findById_ProjectBlueprintUuid(projectUuid, pageable).getContent();
    }

    @Override
    public int getProjectDayMappingsOrder(UUID projectUuid, UUID dayUuid) {
        ProjectDaysMapperId id = new ProjectDaysMapperId(projectUuid, dayUuid);
        if (!projectDaysMapperRepository.existsById(id)) {
            throw new ProjectDayNotFoundException(id);
        }
        return projectDaysMapperRepository.findById(id)
                .orElseThrow(() -> new ProjectDayNotFoundException(id))
                .getSortOrder();
    }

    @Override
    public ProjectDaysMapper updateProjectDayMapping(UUID projectUuid, UUID dayUuid, ProjectDaysMapper updated) {
        ProjectDaysMapperId id = new ProjectDaysMapperId(projectUuid, dayUuid);
        if (!projectDaysMapperRepository.existsById(id)) {
            throw new ProjectDayNotFoundException(id);
        }
        ProjectDaysMapper existing = getProjectDayMapping(projectUuid, dayUuid);
        existing.setSortOrder(updated.getSortOrder());
        return projectDaysMapperRepository.save(existing);
    }

    @Override
    public void deleteProjectDayMapping(UUID projectUuid, UUID dayUuid) {
        ProjectDaysMapperId id = new ProjectDaysMapperId(projectUuid, dayUuid);
        if (!projectDaysMapperRepository.existsById(id)) {
            throw new ProjectDayNotFoundException(id);
        }
        projectDaysMapperRepository.deleteById(id);
    }

    // Project Instance Operations
    @Override
    public ProjectInstance createProjectInstance(ProjectInstance instance) {
        ProjectInstance existing = projectInstanceRepository.findById(instance.getId()).orElse(null);
        if (existing != null && existing.getStatus() == StatusEnum.IN_PROGRESS) {
            return existing;
        }
        instance.setStatus(StatusEnum.IN_PROGRESS);
        return projectInstanceRepository.save(instance);
    }

    @Override
    public ProjectInstance getProjectInstance(UUID projectUuid, UUID userUuid) {
        ProjectInstanceId id = new ProjectInstanceId(projectUuid, userUuid);
        if (!projectInstanceRepository.existsById(id)) {
            throw new ProjectInstanceNotFoundException(id);
        }
        return projectInstanceRepository.findById(id)
                .orElseThrow(() -> new ProjectInstanceNotFoundException(id));
    }

    @Override
    public List<ProjectInstance> getUserProjectInstances(UUID userUuid, int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        return projectInstanceRepository.findById_UserUuid(userUuid, pageable).getContent();
    }

    @Override
    public ProjectInstance updateProjectInstance(UUID projectUuid, UUID userUuid, ProjectInstance updated) {
        ProjectInstanceId id = new ProjectInstanceId(projectUuid, userUuid);
        if (!projectInstanceRepository.existsById(id)) {
            throw new ProjectInstanceNotFoundException(id);
        }
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
        if (!projectInstanceRepository.existsById(id)) {
            throw new ProjectInstanceNotFoundException(id);
        }
        projectInstanceRepository.deleteById(id);
    }

    // DTO Composition
    @Override
    public ProjectDTO getProjectDTO(UUID projectUuid, UUID userUuid) {
        if (!projectBlueprintRepository.existsById(projectUuid)) {
            throw new ProjectBlueprintNotFoundException(projectUuid);
        }
        ProjectInstanceId id = new ProjectInstanceId(projectUuid, userUuid);
        if (!projectInstanceRepository.existsById(id)) {
            throw new ProjectInstanceNotFoundException(id);
        }
        ProjectBlueprint blueprint = getProjectBlueprint(projectUuid);
        ProjectInstance instance = getProjectInstance(projectUuid, userUuid);
        return new ProjectDTO(blueprint, instance);
    }

    @Override
    public List<ProjectDaysMapper> getAllProjectDayMappings(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        return projectDaysMapperRepository.findAll(pageable).getContent();
    }

    @Override
    public List<ProjectInstance> getAllProjectInstances(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        return projectInstanceRepository.findAll(pageable).getContent();
    }
}