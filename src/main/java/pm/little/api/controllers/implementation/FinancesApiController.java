package pm.little.api.controllers.implementation;

import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import pm.little.api.controllers.FinancesApi;
import pm.little.api.models.Finances;
import pm.little.courseservice.FinancesService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Concrete implementation of {@link FinancesApi}. All controller logic is
 * delegated to {@link FinancesService} so the class stays thin and focused on
 * HTTP‑specific concerns.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
@Controller
@RequestMapping("${openapi.projectDay.base-path:}")
public class FinancesApiController implements FinancesApi {

    private final NativeWebRequest request;
    private final FinancesService financesService;

    @Autowired
    public FinancesApiController(NativeWebRequest request, FinancesService financesService) {
        this.request = request;
        this.financesService = financesService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<List<Finances>> financesGet(Integer limit, Integer offset, UUID userUuid) {
        if (limit == null || offset == null) {
            return ResponseEntity.badRequest().build();
        }
        List<Finances> items = financesService.getAllFinances(limit, offset);
        return ResponseEntity.ok(items);
    }

    @Override
    public ResponseEntity<Finances> financesPost(Finances finances) {
        if (finances == null || finances.getId() == null || finances.getId().getProjectBlueprintUuid() == null ||
                finances.getId().getUserUuid() == null) {
            return ResponseEntity.badRequest().build();
        }
        Finances created = financesService.createFinances(finances);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Override
    public ResponseEntity<Void> financesProjectBlueprintUuidUserUuidDelete(UUID projectBlueprintUuid, UUID userUuid) {
        if (projectBlueprintUuid == null || userUuid == null) {
            return ResponseEntity.badRequest().build();
        }
        financesService.deleteFinances(projectBlueprintUuid, userUuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Finances> financesProjectBlueprintUuidUserUuidGet(UUID projectBlueprintUuid, UUID userUuid) {
        if (projectBlueprintUuid == null || userUuid == null) {
            return ResponseEntity.badRequest().build();
        }
        Finances finances = financesService.getFinancesById(projectBlueprintUuid, userUuid);
        return finances != null ? ResponseEntity.ok(finances) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<Finances> financesProjectBlueprintUuidUserUuidPut(UUID projectBlueprintUuid,
                                                                            UUID userUuid,
                                                                            Finances finances) {
        if (projectBlueprintUuid == null || userUuid == null || finances == null ||
                finances.getId() == null || finances.getId().getProjectBlueprintUuid() == null ||
                finances.getId().getUserUuid() == null) {
            return ResponseEntity.badRequest().build();
        }
        Finances updated = financesService.updateFinances(projectBlueprintUuid, userUuid, finances);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
