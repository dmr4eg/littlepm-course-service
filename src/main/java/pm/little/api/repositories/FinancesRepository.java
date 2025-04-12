package pm.little.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import pm.little.api.models.Finances;
import pm.little.api.models.ids.FinancesId;

@Repository
@RepositoryRestResource(exported = false)
public interface FinancesRepository extends JpaRepository<Finances, FinancesId> {
}
