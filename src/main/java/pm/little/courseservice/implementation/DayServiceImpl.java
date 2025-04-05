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

    private DayDTO toDayDTO(DayInstance dayInstance) {
        // Retrieve the dayBlueprint for the dayInstance
        UUID dayBlueprintUuid = dayInstance.getId().getDayBlueprintUuid();
        // Check if the dayBlueprint exists
        if (!dayBlueprintRepository.existsById(dayBlueprintUuid)) {
            throw new DayBlueprintNotFoundException(dayBlueprintUuid);
        }
        DayBlueprint dayBlueprint = dayBlueprintRepository.findById(dayBlueprintUuid)
                .orElseThrow(() -> new DayBlueprintNotFoundException(dayBlueprintUuid));

        // Build the DTO
        DayDTO dto = new DayDTO();
        dto.setBlueprint(dayBlueprint);
        dto.setInstance(dayInstance);
        return dto;
    }

    @Override
    public DayBlueprint createDayBlueprint(DayBlueprint dayBlueprint) {
        DayBlueprint existing = dayBlueprintRepository.findById(dayBlueprint.getDayBlueprintUuid()).orElse(null);
        if (existing != null) {
            return existing;
        }
        return dayBlueprintRepository.save(dayBlueprint);
    }

    @Override
    public List<DayBlueprint> getAllDayBlueprints(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        return dayBlueprintRepository.findAll(pageable).getContent();
    }

    @Override
    public DayBlueprint getDayBlueprint(UUID dayBlueprintUuid) {
        if (!dayBlueprintRepository.existsById(dayBlueprintUuid)) {
            throw new DayBlueprintNotFoundException(dayBlueprintUuid);
        }
        return dayBlueprintRepository.findById(dayBlueprintUuid)
                .orElseThrow(() -> new DayBlueprintNotFoundException(dayBlueprintUuid));
    }

    @Override
    public DayBlueprint updateDayBlueprint(UUID dayBlueprintUuid, DayBlueprint updated) {
        if (!dayBlueprintRepository.existsById(dayBlueprintUuid)) {
            throw new DayBlueprintNotFoundException(dayBlueprintUuid);
        }
        DayBlueprint existing = getDayBlueprint(dayBlueprintUuid);
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setText(updated.getText());
        return dayBlueprintRepository.save(existing);
    }

    @Override
    public void deleteDayBlueprint(UUID dayBlueprintUuid) {
        if (!dayBlueprintRepository.existsById(dayBlueprintUuid)) {
            throw new DayBlueprintNotFoundException(dayBlueprintUuid);
        }
        dayBlueprintRepository.deleteById(dayBlueprintUuid);
    }

    // Day Components Mapper Operations
    @Override
    public DayComponentsMapper createDayComponentMapping(DayComponentsMapper dayComponentsMapper, int sortOrder) {
        DayComponentsMapperId id = dayComponentsMapper.getId();
        UUID dayBlueprintUuid = id.getDayBlueprintUuid();
        UUID componentUuid = id.getComponentUuid();
        if (!dayBlueprintRepository.existsById(dayBlueprintUuid)) {
            throw new DayBlueprintNotFoundException(dayBlueprintUuid);
        }
        DayComponentsMapper existing = dayComponentsMapperRepository.findById(dayComponentsMapper.getId()).orElse(null);
        if (existing != null) {
            return existing;
        }
        dayComponentsMapper.setSortOrder(sortOrder);
        return dayComponentsMapperRepository.save(dayComponentsMapper);
    }

    @Override
    public DayComponentsMapper getDayComponentMapping(UUID dayBlueprintUuid, UUID componentUuid) {
        if (!dayBlueprintRepository.existsById(dayBlueprintUuid)) {
            throw new DayBlueprintNotFoundException(dayBlueprintUuid);
        }
        DayComponentsMapperId id = new DayComponentsMapperId(dayBlueprintUuid, componentUuid);
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
    public DayComponentsMapper updateDayComponentMapping(UUID dayBlueprintUuid, UUID componentUuid, DayComponentsMapper updated) {
        DayComponentsMapperId id = new DayComponentsMapperId(dayBlueprintUuid, componentUuid);
        if (!dayComponentsMapperRepository.existsById(id)) {
            throw new DayComponentNotFoundException(id);
        }
        DayComponentsMapper existing = getDayComponentMapping(dayBlueprintUuid, componentUuid);
        existing.setType(updated.getType());
        return dayComponentsMapperRepository.save(existing);
    }

    @Override
    public void deleteDayComponentMapping(UUID dayBlueprintUuid, UUID componentUuid) {
        DayComponentsMapperId id = new DayComponentsMapperId(dayBlueprintUuid, componentUuid);
        if (!dayComponentsMapperRepository.existsById(id)) {
            throw new DayComponentNotFoundException(id);
        }
        dayComponentsMapperRepository.deleteById(id);
    }

    @Override
    public DayDTO createDayInstance(DayInstance dayInstance) {
        DayInstance existing = dayInstanceRepository.findById(dayInstance.getId()).orElse(null);
        // If it already exists with status IN_PROGRESS, return that same thing
        if (existing != null && existing.getStatus() == StatusEnum.IN_PROGRESS) {
            return toDayDTO(existing);
        }
        // Otherwise, set status and save
        dayInstance.setStatus(StatusEnum.IN_PROGRESS);
        DayInstance saved = dayInstanceRepository.save(dayInstance);
        return toDayDTO(saved);
    }

    @Override
    public DayDTO getDayInstance(UUID dayBlueprintUuid, UUID userUuid) {
        DayInstanceId id = new DayInstanceId(dayBlueprintUuid, userUuid);
        DayInstance dayInstance = dayInstanceRepository.findById(id)
                .orElseThrow(() -> new DayInstanceNotFoundException(id));
        return toDayDTO(dayInstance);
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
    public DayDTO updateDayInstance(UUID dayBlueprintUuid, UUID userUuid, DayInstance dayInstanceUpdated) {
        DayInstanceId id = new DayInstanceId(dayBlueprintUuid, userUuid);
        if (!dayInstanceRepository.existsById(id)) {
            throw new DayInstanceNotFoundException(id);
        }
        DayInstance existing = dayInstanceRepository.findById(id)
                .orElseThrow(() -> new DayInstanceNotFoundException(id));

        existing.setStatus(dayInstanceUpdated.getStatus());
        // If you have more fields you want to update, do them here

        DayInstance saved = dayInstanceRepository.save(existing);
        return toDayDTO(saved);
    }

    @Override
    public void deleteDayInstance(UUID dayBlueprintUuid, UUID userUuid) {
        DayInstanceId id = new DayInstanceId(dayBlueprintUuid, userUuid);
        if (!dayInstanceRepository.existsById(id)) {
            throw new DayInstanceNotFoundException(id);
        }
        dayInstanceRepository.deleteById(id);
    }

    @Override
    public DayDTO getDayDTO(UUID dayBlueprintUuid, UUID userUuid) {
        DayBlueprint dayBlueprint = getDayBlueprint(dayBlueprintUuid);
        DayInstance dayInstance = getDayInstanceRaw(dayBlueprintUuid, userUuid);
        return new DayDTO(dayBlueprint, dayInstance);
    }

    private DayInstance getDayInstanceRaw(UUID dayBlueprintUuid, UUID userUuid) {
        DayInstanceId id = new DayInstanceId(dayBlueprintUuid, userUuid);
        return dayInstanceRepository.findById(id)
                .orElseThrow(() -> new DayInstanceNotFoundException(id));
    }
}
