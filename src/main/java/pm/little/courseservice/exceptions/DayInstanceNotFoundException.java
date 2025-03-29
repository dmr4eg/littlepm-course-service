package pm.little.courseservice.exceptions;

import pm.little.api.models.ids.DayInstanceId;

import java.util.UUID;

public class DayInstanceNotFoundException extends RuntimeException {
    public DayInstanceNotFoundException(DayInstanceId id) {
        super("Day Instance not found with ID: " + id);
    }
}