package pm.little.courseservice.implementation;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pm.little.api.models.DayBlueprint;
import pm.little.api.models.DayComponentsMapper;
import pm.little.api.models.DayInstance;
import pm.little.api.models.dto.DayDTO;
import pm.little.api.models.enums.StatusEnum;
import pm.little.api.models.ids.DayComponentsMapperId;
import pm.little.api.models.ids.DayInstanceId;
import pm.little.api.repositories.DayBlueprintRepository;
import pm.little.api.repositories.DayComponentsMapperRepository;
import pm.little.api.repositories.DayInstanceRepository;
import pm.little.courseservice.DayService;
import pm.little.courseservice.exceptions.DayBlueprintNotFoundException;
import pm.little.courseservice.exceptions.DayComponentNotFoundException;
import pm.little.courseservice.exceptions.DayInstanceNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DayServiceImpl implements DayService {

    private final DayBlueprintRepository dayBlueprintRepository;
    private final DayComponentsMapperRepository dayComponentsMapperRepository;
    private final DayInstanceRepository dayInstanceRepository;

    public DayServiceImpl(
            DayBlueprintRepository dayBlueprintRepository,
            DayComponentsMapperRepository dayComponentsMapperRepository,
            DayInstanceRepository dayInstanceRepository
    ) {
        this.dayBlueprintRepository = dayBlueprintRepository;
        this.dayComponentsMapperRepository = dayComponentsMapperRepository;
        this.dayInstanceRepository = dayInstanceRepository;
    }

    private DayDTO toDayDTO(DayInstance instance) {
        // Retrieve the blueprint for the instance
        UUID blueprintUuid = instance.getId().getDayBlueprintUuid();
        DayBlueprint blueprint = dayBlueprintRepository.findById(blueprintUuid)
                .orElseThrow(() -> new DayBlueprintNotFoundException(blueprintUuid));

        // Build the DTO
        DayDTO dto = new DayDTO();
        dto.setBlueprint(blueprint);
        dto.setInstance(instance);
        return dto;
    }

    @Override
    public DayBlueprint createDayBlueprint(DayBlueprint blueprint) {
        DayBlueprint existing = dayBlueprintRepository.findById(blueprint.getDayBlueprintUuid()).orElse(null);
        if (existing != null) {
            return existing;
        }
        return dayBlueprintRepository.save(blueprint);
    }

    @Override
    public List<DayBlueprint> getAllDayBlueprints(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        return dayBlueprintRepository.findAll(pageable).getContent();
    }

    @Override
    public DayBlueprint getDayBlueprint(UUID dayUuid) {
        if (!dayBlueprintRepository.existsById(dayUuid)) {
            throw new DayBlueprintNotFoundException(dayUuid);
        }
        return dayBlueprintRepository.findById(dayUuid)
                .orElseThrow(() -> new DayBlueprintNotFoundException(dayUuid));
    }

    @Override
    public DayBlueprint updateDayBlueprint(UUID dayUuid, DayBlueprint updated) {
        if (!dayBlueprintRepository.existsById(dayUuid)) {
            throw new DayBlueprintNotFoundException(dayUuid);
        }
        DayBlueprint existing = getDayBlueprint(dayUuid);
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setText(updated.getText());
        return dayBlueprintRepository.save(existing);
    }

    @Override
    public void deleteDayBlueprint(UUID dayUuid) {
        if (!dayBlueprintRepository.existsById(dayUuid)) {
            throw new DayBlueprintNotFoundException(dayUuid);
        }
        dayBlueprintRepository.deleteById(dayUuid);
    }

    // Day Components Mapper Operations
    @Override
    public DayComponentsMapper createDayComponentMapping(DayComponentsMapper mapping, int sortOrder) {
        DayComponentsMapper existing = dayComponentsMapperRepository.findById(mapping.getId()).orElse(null);
        if (existing != null) {
            return existing;
        }
        mapping.setSortOrder(sortOrder);
        return dayComponentsMapperRepository.save(mapping);
    }

    @Override
    public DayComponentsMapper getDayComponentMapping(UUID dayUuid, UUID componentUuid) {
        DayComponentsMapperId id = new DayComponentsMapperId(dayUuid, componentUuid);
        if (!dayComponentsMapperRepository.existsById(id)) {
            throw new DayComponentNotFoundException(id);
        }
        return dayComponentsMapperRepository.findById(id)
                .orElseThrow(() -> new DayComponentNotFoundException(id));
    }

    @Override
    public List<DayComponentsMapper> getDayComponentMappings(UUID id, int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        return dayComponentsMapperRepository.findById_DayBlueprintUuid(id, pageable).getContent();
    }

    @Override
    public DayComponentsMapper updateDayComponentMapping(UUID dayUuid, UUID componentUuid, DayComponentsMapper updated) {
        DayComponentsMapperId id = new DayComponentsMapperId(dayUuid, componentUuid);
        if (!dayComponentsMapperRepository.existsById(id)) {
            throw new DayComponentNotFoundException(id);
        }
        DayComponentsMapper existing = getDayComponentMapping(dayUuid, componentUuid);
        existing.setType(updated.getType());
        return dayComponentsMapperRepository.save(existing);
    }

    @Override
    public void deleteDayComponentMapping(UUID dayUuid, UUID componentUuid) {
        DayComponentsMapperId id = new DayComponentsMapperId(dayUuid, componentUuid);
        if (!dayComponentsMapperRepository.existsById(id)) {
            throw new DayComponentNotFoundException(id);
        }
        dayComponentsMapperRepository.deleteById(id);
    }

    @Override
    public DayDTO createDayInstance(DayInstance instance) {
        DayInstance existing = dayInstanceRepository.findById(instance.getId()).orElse(null);
        // If it already exists with status IN_PROGRESS, return that same thing
        if (existing != null && existing.getStatus() == StatusEnum.IN_PROGRESS) {
            return toDayDTO(existing);
        }
        // Otherwise, set status and save
        instance.setStatus(StatusEnum.IN_PROGRESS);
        DayInstance saved = dayInstanceRepository.save(instance);
        return toDayDTO(saved);
    }

    @Override
    public DayDTO getDayInstance(UUID dayUuid, UUID userUuid) {
        DayInstanceId id = new DayInstanceId(dayUuid, userUuid);
        DayInstance instance = dayInstanceRepository.findById(id)
                .orElseThrow(() -> new DayInstanceNotFoundException(id));
        return toDayDTO(instance);
    }


    @Override
    public List<DayDTO> getUserDayInstancesAsDTO(UUID userUuid, int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        List<DayInstance> rawInstances = dayInstanceRepository.findById_UserUuid(userUuid, pageable).getContent();
        return rawInstances.stream()
                .map(this::toDayDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DayDTO updateDayInstance(UUID dayUuid, UUID userUuid, DayInstance updated) {
        DayInstanceId id = new DayInstanceId(dayUuid, userUuid);
        if (!dayInstanceRepository.existsById(id)) {
            throw new DayInstanceNotFoundException(id);
        }
        DayInstance existing = dayInstanceRepository.findById(id)
                .orElseThrow(() -> new DayInstanceNotFoundException(id));

        existing.setStatus(updated.getStatus());
        // If you have more fields you want to update, do them here

        DayInstance saved = dayInstanceRepository.save(existing);
        return toDayDTO(saved);
    }

    @Override
    public void deleteDayInstance(UUID dayUuid, UUID userUuid) {
        DayInstanceId id = new DayInstanceId(dayUuid, userUuid);
        if (!dayInstanceRepository.existsById(id)) {
            throw new DayInstanceNotFoundException(id);
        }
        dayInstanceRepository.deleteById(id);
    }

    @Override
    public DayDTO getDayDTO(UUID dayUuid, UUID userUuid) {
        DayBlueprint blueprint = getDayBlueprint(dayUuid);
        DayInstance instance = getDayInstanceRaw(dayUuid, userUuid);
        return new DayDTO(blueprint, instance);
    }

    private DayInstance getDayInstanceRaw(UUID dayUuid, UUID userUuid) {
        DayInstanceId id = new DayInstanceId(dayUuid, userUuid);
        return dayInstanceRepository.findById(id)
                .orElseThrow(() -> new DayInstanceNotFoundException(id));
    }
}
