package pm.little.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import pm.little.api.models.DayInstance;
import pm.little.api.models.ids.DayInstanceId;

import java.util.UUID;

@Repository
@RepositoryRestResource(exported = false)
public interface DayInstanceRepository extends JpaRepository<DayInstance, DayInstanceId> {
    Page<DayInstance> findById_UserUuid(UUID userUuid, Pageable pageable);

}
