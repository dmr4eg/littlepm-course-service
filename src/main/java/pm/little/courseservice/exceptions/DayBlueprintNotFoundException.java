package pm.little.courseservice.exceptions;

import java.util.UUID;

public class DayBlueprintNotFoundException extends RuntimeException {
    public DayBlueprintNotFoundException(UUID id) {
        super("Day Blueprint not found with ID: " + id);
    }
}