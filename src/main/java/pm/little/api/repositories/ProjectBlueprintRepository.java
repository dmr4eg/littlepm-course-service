package pm.little.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import pm.little.api.models.ProjectBlueprint;

import java.util.UUID;

@Repository
@RepositoryRestResource(exported = false)
public interface ProjectBlueprintRepository extends JpaRepository<ProjectBlueprint, UUID> {
}
