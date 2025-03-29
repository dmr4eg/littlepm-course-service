package pm.little.courseservice.exceptions;

import pm.little.api.models.ids.DayComponentsMapperId;
import pm.little.api.models.ids.ProjectDaysMapperId;

public class ProjectDayNotFoundException extends RuntimeException {
    public ProjectDayNotFoundException(ProjectDaysMapperId id) {
        super("Project Day not found with ID: " + id);
    }
}