package pm.little.api.controllers.implementation;

import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;
import pm.little.api.controllers.DayInstancesApi;
import pm.little.api.models.DayInstance;
import pm.little.api.models.dto.DayDTO;
import pm.little.api.models.ids.DayInstanceId;
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
public class DayInstancesApiController implements DayInstancesApi {

    private final NativeWebRequest request;
    private final DayService dayService;

    @Autowired
    public DayInstancesApiController(NativeWebRequest request, DayService dayService) {
        this.request = request;
        this.dayService = dayService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    /**
     * DELETE /day-instances/{day_blueprint_uuid}/{user_uuid} : Delete a day instance
     */
    @Override
    public ResponseEntity<Void> dayInstancesDayBlueprintUuidUserUuidDelete(
            UUID dayBlueprintUuid,
            UUID userUuid
    ) {
        if (dayBlueprintUuid == null || userUuid == null) {
            return ResponseEntity.badRequest().build();
        }
        dayService.deleteDayInstance(dayBlueprintUuid, userUuid);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /day-instances/{day_blueprint_uuid}/{user_uuid} : Get a specific day instance
     * Now returning DayDTO
     */
    @Override
    public ResponseEntity<DayDTO> dayInstancesDayBlueprintUuidUserUuidGet(
            UUID dayBlueprintUuid,
            UUID userUuid
    ) {
        if (dayBlueprintUuid == null || userUuid == null) {
            return ResponseEntity.badRequest().build();
        }
        DayDTO dto = dayService.getDayInstance(dayBlueprintUuid, userUuid);
        return ResponseEntity.ok(dto);
    }

    /**
     * PUT /day-instances/{day_blueprint_uuid}/{user_uuid} : Update a day instance
     * Now returning DayDTO
     */
    @Override
    public ResponseEntity<DayDTO> dayInstancesDayBlueprintUuidUserUuidPut(
            UUID dayBlueprintUuid,
            UUID userUuid,
            DayInstance dayInstance
    ) {
        if (dayBlueprintUuid == null || userUuid == null || dayInstance == null) {
            return ResponseEntity.badRequest().build();
        }
        DayDTO updated = dayService.updateDayInstance(dayBlueprintUuid, userUuid, dayInstance);
        return ResponseEntity.ok(updated);
    }

    /**
     * GET /day-instances : List all day instances
     * Now returning List<DayDTO>
     */
    @Override
    public ResponseEntity<List<DayDTO>> dayInstancesGet(
            UUID userUuid,
            Integer limit,
            Integer offset
    ) {
        if (limit == null || offset == null || userUuid == null) {
            return ResponseEntity.badRequest().build();
        }
        List<DayDTO> dtos = dayService.getUserDayInstancesAsDTO(userUuid, limit, offset);
        return ResponseEntity.ok(dtos);
    }

    /**
     * POST /day-instances : Create a day instance
     * Now returning DayDTO
     */
    @Override
    public ResponseEntity<DayDTO> dayInstancesPost(DayInstance dayInstance) {
        DayInstanceId id = dayInstance.getId();
        if (id == null || id.getDayBlueprintUuid() == null || id.getUserUuid() == null || dayInstance == null) {
            return ResponseEntity.badRequest().build();
        }
        DayDTO created = dayService.createDayInstance(dayInstance);
        return ResponseEntity.ok(created);
    }

}
