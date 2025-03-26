package pm.little.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import pm.little.api.models.Project;


import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RepositoryRestResource(exported = false)
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    @Query("SELECT p FROM Project p WHERE p.projectUUID = ?1")
    Optional<Project> findById(UUID id);

    @Query("SELECT p FROM Project p WHERE p.status = ?1")
    List<Project> findByStatus(String status, Pageable pageable);
}
