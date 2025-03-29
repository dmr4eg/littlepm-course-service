package pm.little.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import pm.little.api.models.ProjectDaysMapper;
import pm.little.api.models.ids.ProjectDaysMapperId;

import java.util.UUID;

@Repository
@RepositoryRestResource(exported = false)
public interface ProjectDaysMapperRepository extends JpaRepository<ProjectDaysMapper, ProjectDaysMapperId> {
    Page<ProjectDaysMapper> findByProjectId(UUID projectUuid, Pageable pageable);
}
