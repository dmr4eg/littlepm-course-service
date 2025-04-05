package pm.little.courseservice.exceptions;

import pm.little.api.models.ids.DayComponentsMapperId;

import java.util.UUID;

public class DayComponentNotFoundException extends RuntimeException {
    public DayComponentNotFoundException(DayComponentsMapperId id) {
        super("Day Component not found with ID: " + id);
    }

    public DayComponentNotFoundException(UUID componentUuid) {
        super("Day Component not found with Component ID: " + componentUuid);
    }
}