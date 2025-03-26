package pm.little.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pm.little.api.models.DayBlueprint;

import java.util.UUID;

public interface DayBlueprintRepository extends JpaRepository<DayBlueprint, UUID> {
}
