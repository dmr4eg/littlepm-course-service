package pm.little.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import pm.little.api.models.DayComponentsMapper;
import pm.little.api.models.ids.DayComponentsMapperId;

import java.util.UUID;

@Repository
@RepositoryRestResource(exported = false)
public interface DayComponentsMapperRepository extends JpaRepository<DayComponentsMapper, DayComponentsMapperId> {
    Page<DayComponentsMapper> findByDayId(UUID dayUuid, Pageable pageable);
}
