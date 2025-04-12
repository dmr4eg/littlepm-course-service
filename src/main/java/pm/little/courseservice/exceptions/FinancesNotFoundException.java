package pm.little.courseservice.exceptions;

import pm.little.api.models.ids.FinancesId;

public class FinancesNotFoundException extends RuntimeException {
    public FinancesNotFoundException(FinancesId id) {
        super("Finances not found with ID: " + id);
    }
}