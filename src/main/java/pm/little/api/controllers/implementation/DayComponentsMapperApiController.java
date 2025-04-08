package pm.little.api.controllers.implementation;

import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;
import pm.little.api.controllers.DayComponentsMapperApi;
import pm.little.api.models.DayComponentsMapper;
import pm.little.api.models.ids.DayComponentsMapperId;
import pm.little.courseservice.DayService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Generated(
        value = "org.openapitools.codegen.languages.SpringCodegen",
        date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]",
        comments = "Generator version: 7.11.0"
)
@Controller
public class DayComponentsMapperApiController implements DayComponentsMapperApi {

    private final NativeWebRequest request;
    private final DayService dayService;

    @Autowired
    public DayComponentsMapperApiController(NativeWebRequest request, DayService dayService) {
        this.request = request;
        this.dayService = dayService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    /**
     * DELETE /day-components-mapper/{day_blueprint_uuid}/{day_component_uuid}
     */
    @Override
    public ResponseEntity<Void> dayComponentsMapperDayBlueprintUuidComponentUuidDelete(
            UUID dayBlueprintUuid,
            UUID componentUuid
    ) {
        if (dayBlueprintUuid == null || componentUuid == null) {
            return ResponseEntity.badRequest().build();
        }
        dayService.deleteDayComponentMapping(dayBlueprintUuid, componentUuid);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /day-components-mapper/{day_blueprint_uuid}/{day_component_uuid}
     */
    @Override
    public ResponseEntity<DayComponentsMapper> dayComponentsMapperDayBlueprintUuidComponentUuidGet(
            UUID dayBlueprintUuid,
            UUID componentUuid
    ) {
        if (dayBlueprintUuid == null || componentUuid == null) {
            return ResponseEntity.badRequest().build();
        }
        DayComponentsMapper mapping = dayService.getDayComponentMapping(dayBlueprintUuid, componentUuid);
        return ResponseEntity.ok(mapping);
    }

    /**
     * PUT /day-components-mapper/{day_blueprint_uuid}/{day_component_uuid}
     */
    @Override
    public ResponseEntity<DayComponentsMapper> dayComponentsMapperDayBlueprintUuidComponentUuidPut(
            UUID dayBlueprintUuid,
            UUID componentUuid,
            DayComponentsMapper dayComponentsMapper
    ) {
        if (dayBlueprintUuid == null || componentUuid == null || dayComponentsMapper == null) {
            return ResponseEntity.badRequest().build();
        }
        DayComponentsMapper updated = dayService.updateDayComponentMapping(dayBlueprintUuid, componentUuid, dayComponentsMapper);
        return ResponseEntity.ok(updated);
    }

    /**
     * GET /day-components-mapper (list all mappings)
     */
    @Override
    public ResponseEntity<List<DayComponentsMapper>> dayComponentsMapperGet(Integer limit, Integer offset) {
        if (limit == null || offset == null) {
            return ResponseEntity.badRequest().build();
        }
        List<DayComponentsMapper> list = dayService.getDayComponentMappings(limit, offset);
        return ResponseEntity.ok(list);
    }

    /**
     * POST /day-components-mapper (create a new mapping)
     */
    @Override
    public ResponseEntity<DayComponentsMapper> dayComponentsMapperPost(DayComponentsMapper dayComponentsMapper) {
        DayComponentsMapperId id = dayComponentsMapper.getId();
        if (id == null || id.getDayBlueprintUuid() == null || id.getComponentUuid() == null || dayComponentsMapper == null) {
            return ResponseEntity.badRequest().build();
        }
        final int defaultOrder = 0;
        DayComponentsMapper created = dayService.createDayComponentMapping(dayComponentsMapper, defaultOrder);
        return ResponseEntity.ok(created);
    }
}
