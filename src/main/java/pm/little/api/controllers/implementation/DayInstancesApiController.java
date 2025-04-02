package pm.little.api.controllers.implementation;

import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;
import pm.little.api.controllers.DayInstancesApi;
import pm.little.api.models.DayInstance;
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
        dayService.deleteDayInstance(dayBlueprintUuid, userUuid);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /day-instances/{day_blueprint_uuid}/{user_uuid} : Get a specific day instance
     */
    @Override
    public ResponseEntity<DayInstance> dayInstancesDayBlueprintUuidUserUuidGet(
            UUID dayBlueprintUuid,
            UUID userUuid
    ) {
        DayInstance instance = dayService.getDayInstance(dayBlueprintUuid, userUuid);
        return ResponseEntity.ok(instance);
    }

    /**
     * PUT /day-instances/{day_blueprint_uuid}/{user_uuid} : Update a day instance
     */
    @Override
    public ResponseEntity<DayInstance> dayInstancesDayBlueprintUuidUserUuidPut(
            UUID dayBlueprintUuid,
            UUID userUuid,
            DayInstance dayInstance
    ) {
        DayInstance updated = dayService.updateDayInstance(dayBlueprintUuid, userUuid, dayInstance);
        return ResponseEntity.ok(updated);
    }

    /**
     * GET /day-instances : List all day instances
     *
     * NOTE:
     * - If you do not have a service method to retrieve *all* DayInstances,
     *   you will need to implement one (e.g., `dayService.getAllDayInstances()`).
     * - Adjust as needed if you only want to list day instances for a specific user,
     *   or if you have pagination, etc.
     */
//    @Override
//    public ResponseEntity<List<DayInstance>> dayInstancesGet(UUID userUuid) {
//        List<DayInstance> list = dayService.getUserDayInstances(userUuid);
//        return ResponseEntity.ok(list);
//    }

    /**
     * POST /day-instances : Create a day instance
     */
    @Override
    public ResponseEntity<DayInstance> dayInstancesPost(DayInstance dayInstance) {
        DayInstance created = dayService.createDayInstance(dayInstance);
        return ResponseEntity.ok(created);
    }

}
