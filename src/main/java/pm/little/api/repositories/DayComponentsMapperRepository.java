package pm.little.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pm.little.api.models.DayComponentsMapper;

import java.util.UUID;

public interface DayComponentsMapperRepository extends JpaRepository<DayComponentsMapper, UUID> {
}
