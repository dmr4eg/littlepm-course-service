package pm.little.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pm.little.api.models.ProjectBlueprint;

import java.util.UUID;

public interface ProjectBlueprintRepository extends JpaRepository<ProjectBlueprint, UUID> {
}
