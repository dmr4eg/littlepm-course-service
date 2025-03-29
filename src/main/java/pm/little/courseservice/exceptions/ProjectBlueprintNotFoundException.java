package pm.little.courseservice.exceptions;

import pm.little.api.models.ProjectBlueprint;
import pm.little.api.models.ids.ProjectDaysMapperId;

import java.util.UUID;

public class ProjectBlueprintNotFoundException extends RuntimeException {
    public ProjectBlueprintNotFoundException(UUID id) {
        super("Project Blueprint not found with ID: " + id);
    }
}