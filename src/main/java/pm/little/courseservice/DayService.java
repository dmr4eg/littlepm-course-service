package pm.little.courseservice;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pm.little.api.models.DayBlueprint;
import pm.little.api.models.DayComponentsMapper;
import pm.little.api.models.DayInstance;
import pm.little.api.models.dto.DayDTO;
import pm.little.api.models.ids.DayComponentsMapperId;
import pm.little.api.models.ids.DayInstanceId;

import java.util.List;
import java.util.UUID;

public interface DayService  {

    // DAY BLUEPRINTS

    public DayBlueprint createDayBlueprint(DayBlueprint blueprint)  ;

    public List<DayBlueprint> getAllDayBlueprints(int limit, int offset)  ;

    public DayBlueprint getDayBlueprint(UUID dayUuid)  ;

    public DayBlueprint updateDayBlueprint(UUID dayUuid, DayBlueprint updated)  ;

    public void deleteDayBlueprint(UUID dayUuid)  ;

    // DAY COMPONENTS MAPPINGS

    public DayComponentsMapper createDayComponentMapping(DayComponentsMapper mapping, int sortOrder) ;

    public DayComponentsMapper getDayComponentMapping(UUID dayUuid, UUID componentUuid)  ;

    public List<DayComponentsMapper> getDayComponentMappings(UUID dayUuid, int limit, int offset)  ;

    public List<DayComponentsMapper> getDayComponentMappings(int limit, int offset)  ;

    public DayComponentsMapper updateDayComponentMapping(UUID dayUuid, UUID componentUuid, DayComponentsMapper updated)  ;

    public void deleteDayComponentMapping(UUID dayUuid, UUID componentUuid)  ;

    // DAY INSTANCES

    public DayDTO createDayInstance(DayInstance instance)  ;

    public DayDTO getDayInstance(UUID dayUuid, UUID userUuid)  ;

    public List<DayDTO> getUserDayInstancesAsDTO(UUID userUuid, int limit, int offset) ;

    public DayDTO updateDayInstance(UUID dayUuid, UUID userUuid, DayInstance updated)  ;

    public void deleteDayInstance(UUID dayUuid, UUID userUuid)  ;
     
    public DayDTO getDayDTO(UUID dayUuid, UUID userUuid)  ;

}
