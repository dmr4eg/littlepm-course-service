package pm.little.courseservice.implementation;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pm.little.api.models.*;
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

    // Day Instance Operations
    @Override
    public DayInstance createDayInstance(DayInstance instance) {
        DayInstance existing = dayInstanceRepository.findById(instance.getId()).orElse(null);
        if (existing != null && existing.getStatus() == StatusEnum.IN_PROGRESS) {
            return existing;
        }
        instance.setStatus(StatusEnum.IN_PROGRESS);
        return dayInstanceRepository.save(instance);
    }

    @Override
    public DayInstance getDayInstance(UUID dayUuid, UUID userUuid) {
        DayInstanceId id = new DayInstanceId(dayUuid, userUuid);
        if (!dayInstanceRepository.existsById(id)) {
            throw new DayInstanceNotFoundException(id);
        }
        return dayInstanceRepository.findById(id)
                .orElseThrow(() -> new DayInstanceNotFoundException(id));
    }

    @Override
    public List<DayInstance> getUserDayInstances(UUID userUuid, int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        return dayInstanceRepository.findById_UserUuid(userUuid, pageable).getContent();
    }

    @Override
    public DayInstance updateDayInstance(UUID dayUuid, UUID userUuid, DayInstance updated) {
        DayInstanceId id = new DayInstanceId(dayUuid, userUuid);
        if (!dayInstanceRepository.existsById(id)) {
            throw new DayInstanceNotFoundException(id);
        }
        DayInstance existing = getDayInstance(dayUuid, userUuid);
        existing.setStatus(updated.getStatus());
        return dayInstanceRepository.save(existing);
    }

    @Override
    public void deleteDayInstance(UUID dayUuid, UUID userUuid) {
        DayInstanceId id = new DayInstanceId(dayUuid, userUuid);
        if (!dayInstanceRepository.existsById(id)) {
            throw new DayInstanceNotFoundException(id);
        }
        dayInstanceRepository.deleteById(id);
    }

    // DTO Composition
    @Override
    public DayDTO getDayDTO(UUID dayUuid, UUID userUuid) {
        DayBlueprint blueprint = getDayBlueprint(dayUuid);
        DayInstance instance = getDayInstance(dayUuid, userUuid);
        return new DayDTO(blueprint, instance);
    }
}