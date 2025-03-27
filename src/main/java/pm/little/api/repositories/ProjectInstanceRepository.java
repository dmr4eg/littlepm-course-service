package pm.little.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pm.little.api.models.ProjectInstance;

import java.util.UUID;

public interface ProjectInstanceRepository extends JpaRepository<ProjectInstance, UUID> {
}
