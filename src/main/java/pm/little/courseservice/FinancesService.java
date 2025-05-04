package pm.little.courseservice;

import pm.little.api.models.Finances;

import java.util.List;
import java.util.UUID;

public interface FinancesService {
    public List<Finances> getAllFinances() ;

    public Finances getFinancesById(UUID projectBlueprintUuid, UUID userUuid) ;

    public Finances createFinances(Finances finances) ;

    public Finances updateFinances(UUID projectBlueprintUuid, UUID userUuid, Finances financesDetails) ;

    public void deleteFinances(UUID projectBlueprintUuid, UUID userUuid) ;

    public List<Finances> getAllFinances(int limit, int offset);
}
