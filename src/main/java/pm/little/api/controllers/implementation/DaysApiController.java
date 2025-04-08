package pm.little.api.controllers.implementation;

import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;
import pm.little.api.controllers.DaysApi;
import pm.little.api.models.DayBlueprint;
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
public class DaysApiController implements DaysApi {

    private final NativeWebRequest request;
    private final DayService dayService;

    @Autowired
    public DaysApiController(NativeWebRequest request, DayService dayService) {
        this.request = request;
        this.dayService = dayService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> daysDayBlueprintUuidDelete(UUID dayBlueprintUuid) {
        if (dayBlueprintUuid == null) {
            return ResponseEntity.badRequest().build();
        }
        dayService.deleteDayBlueprint(dayBlueprintUuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<DayBlueprint> daysDayBlueprintUuidGet(UUID dayBlueprintUuid) {
        if (dayBlueprintUuid == null) {
            return ResponseEntity.badRequest().build();
        }
        DayBlueprint blueprint = dayService.getDayBlueprint(dayBlueprintUuid);
        return ResponseEntity.ok(blueprint);
    }

    @Override
    public ResponseEntity<DayBlueprint> daysDayBlueprintUuidPut(UUID dayBlueprintUuid, DayBlueprint dayBlueprint) {
        if (dayBlueprintUuid == null || dayBlueprint == null) {
            return ResponseEntity.badRequest().build();
        }
        DayBlueprint updated = dayService.updateDayBlueprint(dayBlueprintUuid, dayBlueprint);
        return ResponseEntity.ok(updated);
    }

    @Override
    public ResponseEntity<List<DayBlueprint>> daysGet(Integer limit, Integer offset) {
        if (limit == null || offset == null) {
            return ResponseEntity.badRequest().build();
        }
        List<DayBlueprint> list = dayService.getAllDayBlueprints(limit, offset);
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<DayBlueprint> daysPost(DayBlueprint dayBlueprint) {
        if (dayBlueprint == null) {
            return ResponseEntity.badRequest().build();
        }
        DayBlueprint created = dayService.createDayBlueprint(dayBlueprint);
        return ResponseEntity.ok(created);
    }

}
