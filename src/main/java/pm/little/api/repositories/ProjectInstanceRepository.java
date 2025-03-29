package pm.little.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import pm.little.api.models.ProjectInstance;
import pm.little.api.models.ids.ProjectInstanceId;

import java.util.UUID;

@Repository
@RepositoryRestResource(exported = false)
public interface ProjectInstanceRepository extends JpaRepository<ProjectInstance, ProjectInstanceId> {
    Page<ProjectInstance> findByUser(UUID userUuid, Pageable pageable);
}
