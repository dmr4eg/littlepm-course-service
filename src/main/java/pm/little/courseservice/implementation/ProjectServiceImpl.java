package pm.little.courseservice.implementation;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pm.little.api.models.ProjectBlueprint;
import pm.little.api.models.ProjectDaysMapper;
import pm.little.api.models.ProjectInstance;
import pm.little.api.models.dto.ProjectDTO;
import pm.little.api.models.enums.StatusEnum;
import pm.little.api.models.ids.ProjectDaysMapperId;
import pm.little.api.models.ids.ProjectInstanceId;
import pm.little.api.repositories.DayBlueprintRepository;
import pm.little.api.repositories.ProjectBlueprintRepository;
import pm.little.api.repositories.ProjectDaysMapperRepository;
import pm.little.api.repositories.ProjectInstanceRepository;
import pm.little.courseservice.ProjectService;
import pm.little.courseservice.exceptions.DayBlueprintNotFoundException;
import pm.little.courseservice.exceptions.ProjectBlueprintNotFoundException;
import pm.little.courseservice.exceptions.ProjectDayNotFoundException;
import pm.little.courseservice.exceptions.ProjectInstanceNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectBlueprintRepository projectBlueprintRepository;
    private final ProjectDaysMapperRepository projectDaysMapperRepository;
    private final ProjectInstanceRepository projectInstanceRepository;
    private final DayBlueprintRepository dayBlueprintRepository;

    public ProjectServiceImpl(
            ProjectBlueprintRepository projectBlueprintRepository,
            ProjectDaysMapperRepository projectDaysMapperRepository,
            ProjectInstanceRepository projectInstanceRepository,
            DayBlueprintRepository dayBlueprintRepository) {
        this.projectBlueprintRepository = projectBlueprintRepository;
        this.projectDaysMapperRepository = projectDaysMapperRepository;
        this.projectInstanceRepository = projectInstanceRepository;
        this.dayBlueprintRepository = dayBlueprintRepository;
    }

    private ProjectDTO toProjectDTO(ProjectInstance projectInstance) {
        UUID projectBlueprintUuid = projectInstance.getId().getProjectBlueprintUuid();
        if (!projectBlueprintRepository.existsById(projectBlueprintUuid)) {
            throw new ProjectBlueprintNotFoundException(projectBlueprintUuid);
        }
        ProjectBlueprint projectBlueprint = projectBlueprintRepository.findById(projectBlueprintUuid)
                .orElseThrow(() -> new ProjectBlueprintNotFoundException(projectBlueprintUuid));
        ProjectDTO dto = new ProjectDTO();
        dto.setBlueprint(projectBlueprint);
        dto.setInstance(projectInstance);
        return dto;
    }

    @Override
    public ProjectBlueprint createProjectBlueprint(ProjectBlueprint projectBlueprint) {
        ProjectBlueprint existing = projectBlueprintRepository.findById(projectBlueprint.getProjectBlueprintUuid())
                .orElse(null);
        if (existing != null) {
            return existing;
        }
        return projectBlueprintRepository.save(projectBlueprint);
    }

    @Override
    public List<ProjectBlueprint> getAllProjectBlueprints(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        return projectBlueprintRepository.findAll(pageable).getContent();
    }

    @Override
    public ProjectBlueprint getProjectBlueprint(UUID projectBlueprintUuid) {
        if (!projectBlueprintRepository.existsById(projectBlueprintUuid)) {
            throw new ProjectBlueprintNotFoundException(projectBlueprintUuid);
        }
        return projectBlueprintRepository.findById(projectBlueprintUuid)
                .orElseThrow(() -> new ProjectBlueprintNotFoundException(projectBlueprintUuid));
    }

    @Override
    public ProjectBlueprint updateProjectBlueprint(UUID projectBlueprintUuid,
            ProjectBlueprint projectBlueprintUpdated) {
        if (!projectBlueprintRepository.existsById(projectBlueprintUuid)) {
            throw new ProjectBlueprintNotFoundException(projectBlueprintUuid);
        }
        ProjectBlueprint existing = getProjectBlueprint(projectBlueprintUuid);
        existing.setTitle(projectBlueprintUpdated.getTitle());
        existing.setDescription(projectBlueprintUpdated.getDescription());
        existing.setDifficulty(projectBlueprintUpdated.getDifficulty());
        existing.setStyle(projectBlueprintUpdated.getStyle());
        existing.setPosterUrl(projectBlueprintUpdated.getPosterUrl());
        existing.setWelcomeVideoUrl(projectBlueprintUpdated.getWelcomeVideoUrl());
        return projectBlueprintRepository.save(existing);
    }

    @Override
    public void deleteProjectBlueprint(UUID projectBlueprintUuid) {
        if (!projectBlueprintRepository.existsById(projectBlueprintUuid)) {
            throw new ProjectBlueprintNotFoundException(projectBlueprintUuid);
        }
        projectBlueprintRepository.deleteById(projectBlueprintUuid);
    }

    @Override
    public ProjectDaysMapper createProjectDayMapping(ProjectDaysMapper projectDaysMapper, int sortOrder) {
        ProjectDaysMapperId projectDaysMapperId = projectDaysMapper.getId();
        UUID projectBlueprintUuid = projectDaysMapperId.getProjectBlueprintUuid();
        UUID dayBlueprintUuid = projectDaysMapperId.getDayBlueprintUuid();
        if (!projectBlueprintRepository.existsById(projectBlueprintUuid)) {
            throw new ProjectBlueprintNotFoundException(projectBlueprintUuid);
        }
        if (!dayBlueprintRepository.existsById(dayBlueprintUuid)) {
            throw new DayBlueprintNotFoundException(dayBlueprintUuid);
        }
        ProjectDaysMapper existing = projectDaysMapperRepository.findById(projectDaysMapper.getId()).orElse(null);
        if (existing != null) {
            return existing;
        }
        projectDaysMapper.setSortOrder(sortOrder);
        return projectDaysMapperRepository.save(projectDaysMapper);
    }

    @Override
    public ProjectDaysMapper getProjectDayMapping(UUID projectBlueprintUuid, UUID dayBlueprintUuid) {
        if (!projectBlueprintRepository.existsById(projectBlueprintUuid)) {
            throw new ProjectBlueprintNotFoundException(projectBlueprintUuid);
        }
        if (!dayBlueprintRepository.existsById(dayBlueprintUuid)) {
            throw new DayBlueprintNotFoundException(dayBlueprintUuid);
        }
        ProjectDaysMapperId id = new ProjectDaysMapperId(projectBlueprintUuid, dayBlueprintUuid);
        if (!projectDaysMapperRepository.existsById(id)) {
            throw new ProjectDayNotFoundException(id);
        }
        return projectDaysMapperRepository.findById(id)
                .orElseThrow(() -> new ProjectDayNotFoundException(id));
    }

    @Override
    public List<ProjectDaysMapper> getProjectDayMappings(UUID projectBlueprintUuid, int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        return projectDaysMapperRepository.findById_ProjectBlueprintUuid(projectBlueprintUuid, pageable).getContent();
    }

    @Override
    public int getProjectDayMappingsOrder(UUID projectBlueprintUuid, UUID dayBlueprintUuid) {
        if (!projectBlueprintRepository.existsById(projectBlueprintUuid)) {
            throw new ProjectBlueprintNotFoundException(projectBlueprintUuid);
        }
        if (!dayBlueprintRepository.existsById(dayBlueprintUuid)) {
            throw new DayBlueprintNotFoundException(dayBlueprintUuid);
        }
        ProjectDaysMapperId id = new ProjectDaysMapperId(projectBlueprintUuid, dayBlueprintUuid);
        if (!projectDaysMapperRepository.existsById(id)) {
            throw new ProjectDayNotFoundException(id);
        }
        return projectDaysMapperRepository.findById(id)
                .orElseThrow(() -> new ProjectDayNotFoundException(id))
                .getSortOrder();
    }

    @Override
    public ProjectDaysMapper updateProjectDayMapping(UUID projectBlueprintUuid, UUID dayBlueprintUuid,
            ProjectDaysMapper projectDaysMapperUpdated) {
        if (!projectBlueprintRepository.existsById(projectBlueprintUuid)) {
            throw new ProjectBlueprintNotFoundException(projectBlueprintUuid);
        }
        if (!dayBlueprintRepository.existsById(dayBlueprintUuid)) {
            throw new DayBlueprintNotFoundException(dayBlueprintUuid);
        }
        ProjectDaysMapperId id = new ProjectDaysMapperId(projectBlueprintUuid, dayBlueprintUuid);
        if (!projectDaysMapperRepository.existsById(id)) {
            throw new ProjectDayNotFoundException(id);
        }
        ProjectDaysMapper existing = getProjectDayMapping(projectBlueprintUuid, dayBlueprintUuid);
        existing.setSortOrder(projectDaysMapperUpdated.getSortOrder());
        return projectDaysMapperRepository.save(existing);
    }

    @Override
    public void deleteProjectDayMapping(UUID projectBlueprintUuid, UUID dayBlueprintUuid) {
        if (!projectBlueprintRepository.existsById(projectBlueprintUuid)) {
            throw new ProjectBlueprintNotFoundException(projectBlueprintUuid);
        }
        if (!dayBlueprintRepository.existsById(dayBlueprintUuid)) {
            throw new DayBlueprintNotFoundException(dayBlueprintUuid);
        }
        ProjectDaysMapperId id = new ProjectDaysMapperId(projectBlueprintUuid, dayBlueprintUuid);
        if (!projectDaysMapperRepository.existsById(id)) {
            throw new ProjectDayNotFoundException(id);
        }
        projectDaysMapperRepository.deleteById(id);
    }

    @Override
    public ProjectDTO createProjectInstance(ProjectInstance projectInstance) {
        ProjectInstanceId instanceId = projectInstance.getId();
        UUID projectBlueprintUuid = instanceId.getProjectBlueprintUuid();
        if (!projectBlueprintRepository.existsById(projectBlueprintUuid)) {
            throw new ProjectBlueprintNotFoundException(projectBlueprintUuid);
        }
        ProjectInstance existing = projectInstanceRepository.findById(instanceId).orElse(null);
        if (existing != null && existing.getStatus() == StatusEnum.IN_PROGRESS) {
            return toProjectDTO(existing);
        }
        projectInstance.setStatus(StatusEnum.IN_PROGRESS);
        ProjectInstance saved = projectInstanceRepository.save(projectInstance);
        return toProjectDTO(saved);
    }

    @Override
    public ProjectDTO getProjectInstance(UUID projectBlueprintUuid, UUID userUuid) {
        if (!projectBlueprintRepository.existsById(projectBlueprintUuid)) {
            throw new ProjectBlueprintNotFoundException(projectBlueprintUuid);
        }
        ProjectInstanceId id = new ProjectInstanceId(projectBlueprintUuid, userUuid);
        ProjectInstance instance = projectInstanceRepository.findById(id)
                .orElseThrow(() -> new ProjectInstanceNotFoundException(id));
        return toProjectDTO(instance);
    }

    @Override
    public List<ProjectDTO> getUserProjectInstancesAsDTO(UUID userUuid, int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        List<ProjectInstance> instances = projectInstanceRepository.findById_UserUuid(userUuid, pageable).getContent();
        return instances.stream()
                .map(this::toProjectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> getAllProjectInstancesAsDTO(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        List<ProjectInstance> instances = projectInstanceRepository.findAll(pageable).getContent();
        return instances.stream()
                .map(this::toProjectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO updateProjectInstance(UUID projectBlueprintUuid, UUID userUuid,
            ProjectInstance projectInstanceUpdated) {
        if (!projectBlueprintRepository.existsById(projectBlueprintUuid)) {
            throw new ProjectBlueprintNotFoundException(projectBlueprintUuid);
        }
        ProjectInstanceId id = new ProjectInstanceId(projectBlueprintUuid, userUuid);
        if (!projectInstanceRepository.existsById(id)) {
            throw new ProjectInstanceNotFoundException(id);
        }
        ProjectInstance existing = projectInstanceRepository.findById(id)
                .orElseThrow(() -> new ProjectInstanceNotFoundException(id));

        existing.setStatus(projectInstanceUpdated.getStatus());
        existing.setStartDate(projectInstanceUpdated.getStartDate());
        existing.setEndDate(projectInstanceUpdated.getEndDate());
        existing.setFeedback(projectInstanceUpdated.getFeedback());
        existing.setWhatWentWell(projectInstanceUpdated.getWhatWentWell());

        ProjectInstance saved = projectInstanceRepository.save(existing);
        return toProjectDTO(saved);
    }

    @Override
    public void deleteProjectInstance(UUID projectBlueprintUuid, UUID userUuid) {
        if (!projectBlueprintRepository.existsById(projectBlueprintUuid)) {
            throw new ProjectBlueprintNotFoundException(projectBlueprintUuid);
        }
        ProjectInstanceId id = new ProjectInstanceId(projectBlueprintUuid, userUuid);
        if (!projectInstanceRepository.existsById(id)) {
            throw new ProjectInstanceNotFoundException(id);
        }
        projectInstanceRepository.deleteById(id);
    }

    @Override
    public ProjectDTO getProjectDTO(UUID projectBlueprintUuid, UUID userUuid) {
        if (!projectBlueprintRepository.existsById(projectBlueprintUuid)) {
            throw new ProjectBlueprintNotFoundException(projectBlueprintUuid);
        }
        ProjectBlueprint projectBlueprint = getProjectBlueprint(projectBlueprintUuid);
        ProjectInstance projectInstance = getProjectInstanceRaw(projectBlueprintUuid, userUuid);
        return new ProjectDTO(projectBlueprint, projectInstance);
    }

    private ProjectInstance getProjectInstanceRaw(UUID projectBlueprintUuid, UUID userUuid) {
        if (!projectBlueprintRepository.existsById(projectBlueprintUuid)) {
            throw new ProjectBlueprintNotFoundException(projectBlueprintUuid);
        }
        ProjectInstanceId id = new ProjectInstanceId(projectBlueprintUuid, userUuid);
        return projectInstanceRepository.findById(id)
                .orElseThrow(() -> new ProjectInstanceNotFoundException(id));
    }

    @Override
    public List<ProjectDaysMapper> getAllProjectDayMappings(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        return projectDaysMapperRepository.findAll(pageable).getContent();
    }
}
