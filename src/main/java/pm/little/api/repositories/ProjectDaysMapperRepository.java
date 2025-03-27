package pm.little.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pm.little.api.models.ProjectDaysMapper;

import java.util.UUID;

public interface ProjectDaysMapperRepository extends JpaRepository<ProjectDaysMapper, UUID> {
}
