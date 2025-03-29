package pm.little.courseservice.exceptions;

import pm.little.api.models.ids.ProjectDaysMapperId;
import pm.little.api.models.ids.ProjectInstanceId;

public class ProjectInstanceNotFoundException extends RuntimeException {
    public ProjectInstanceNotFoundException(ProjectInstanceId id) {
        super("Project Instance not found with ID: " + id);
    }
}