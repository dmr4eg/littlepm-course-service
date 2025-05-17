package pm.little.courseservice.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pm.little.api.models.ProjectBlueprint;
import pm.little.api.models.enums.DifficultyEnum;
import pm.little.api.models.enums.StyleEnum;
import pm.little.api.repositories.ProjectBlueprintRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProjectBlueprintRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProjectBlueprintRepository projectBlueprintRepository;

    private ProjectBlueprint projectBlueprint1;
    private ProjectBlueprint projectBlueprint2;
    private ProjectBlueprint projectBlueprint3;

    @BeforeEach
    void setUp() {
        // Create test data
        projectBlueprint1 = new ProjectBlueprint()
                .projectBlueprintUuid(UUID.randomUUID())
                .title("Project 1")
                .description("Description 1")
                .difficulty(DifficultyEnum.EASY)
                .style(StyleEnum.DIY)
                .posterUrl("https://example.com/poster1.jpg")
                .welcomeVideoUrl("https://example.com/welcome1.mp4");

        projectBlueprint2 = new ProjectBlueprint()
                .projectBlueprintUuid(UUID.randomUUID())
                .title("Project 2")
                .description("Description 2")
                .difficulty(DifficultyEnum.MEDIUM)
                .style(StyleEnum.READY_MADE)
                .posterUrl("https://example.com/poster2.jpg")
                .welcomeVideoUrl("https://example.com/welcome2.mp4");

        projectBlueprint3 = new ProjectBlueprint()
                .projectBlueprintUuid(UUID.randomUUID())
                .title("Project 3")
                .description("Description 3")
                .difficulty(DifficultyEnum.HARD)
                .style(StyleEnum.DIY)
                .posterUrl("https://example.com/poster3.jpg")
                .welcomeVideoUrl("https://example.com/welcome3.mp4");

        // Persist test data
        entityManager.persist(projectBlueprint1);
        entityManager.persist(projectBlueprint2);
        entityManager.persist(projectBlueprint3);
        entityManager.flush();
    }

    @Test
    @DisplayName("Should find project blueprint by UUID")
    void shouldFindProjectBlueprintByUuid() {
        Optional<ProjectBlueprint> found = projectBlueprintRepository.findById(projectBlueprint1.getProjectBlueprintUuid());

        assertTrue(found.isPresent());
        assertEquals(projectBlueprint1.getTitle(), found.get().getTitle());
        assertEquals(projectBlueprint1.getDescription(), found.get().getDescription());
    }

    @Test
    @DisplayName("Should return empty when finding by non-existent UUID")
    void shouldReturnEmptyWhenFindingByNonExistentUuid() {
        Optional<ProjectBlueprint> found = projectBlueprintRepository.findById(UUID.randomUUID());

        assertFalse(found.isPresent());
    }

    @Test
    @DisplayName("Should find all project blueprints")
    void shouldFindAllProjectBlueprints() {
        List<ProjectBlueprint> found = projectBlueprintRepository.findAll();

        assertNotNull(found);
        assertEquals(3, found.size());
    }

    @Test
    @DisplayName("Should find all project blueprints with pagination")
    void shouldFindAllProjectBlueprintsWithPagination() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<ProjectBlueprint> found = projectBlueprintRepository.findAll(pageable);

        assertNotNull(found);
        assertEquals(2, found.getContent().size());
        assertEquals(3, found.getTotalElements());
        assertEquals(2, found.getPageable().getPageSize());
    }

    @Test
    @DisplayName("Should save project blueprint")
    void shouldSaveProjectBlueprint() {
        ProjectBlueprint newProjectBlueprint = new ProjectBlueprint()
                .projectBlueprintUuid(UUID.randomUUID())
                .title("New Project")
                .description("New Description")
                .difficulty(DifficultyEnum.EASY)
                .style(StyleEnum.DIY)
                .posterUrl("https://example.com/new-poster.jpg")
                .welcomeVideoUrl("https://example.com/new-welcome.mp4");

        ProjectBlueprint saved = projectBlueprintRepository.save(newProjectBlueprint);

        assertNotNull(saved);
        assertEquals(newProjectBlueprint.getProjectBlueprintUuid(), saved.getProjectBlueprintUuid());
        assertEquals(newProjectBlueprint.getTitle(), saved.getTitle());
        
        Optional<ProjectBlueprint> found = projectBlueprintRepository.findById(saved.getProjectBlueprintUuid());
        assertTrue(found.isPresent());
    }

    @Test
    @DisplayName("Should update project blueprint")
    void shouldUpdateProjectBlueprint() {
        ProjectBlueprint toUpdate = projectBlueprintRepository.findById(projectBlueprint1.getProjectBlueprintUuid()).get();
        toUpdate.setTitle("Updated Title");
        toUpdate.setDescription("Updated Description");

        ProjectBlueprint updated = projectBlueprintRepository.save(toUpdate);

        assertNotNull(updated);
        assertEquals("Updated Title", updated.getTitle());
        assertEquals("Updated Description", updated.getDescription());
        
        Optional<ProjectBlueprint> found = projectBlueprintRepository.findById(projectBlueprint1.getProjectBlueprintUuid());
        assertTrue(found.isPresent());
        assertEquals("Updated Title", found.get().getTitle());
    }

    @Test
    @DisplayName("Should delete project blueprint")
    void shouldDeleteProjectBlueprint() {
        projectBlueprintRepository.deleteById(projectBlueprint1.getProjectBlueprintUuid());

        Optional<ProjectBlueprint> found = projectBlueprintRepository.findById(projectBlueprint1.getProjectBlueprintUuid());
        assertFalse(found.isPresent());
        
        List<ProjectBlueprint> all = projectBlueprintRepository.findAll();
        assertEquals(2, all.size());
    }

    @Test
    @DisplayName("Should check if project blueprint exists by UUID")
    void shouldCheckIfProjectBlueprintExistsByUuid() {
        boolean exists = projectBlueprintRepository.existsById(projectBlueprint1.getProjectBlueprintUuid());
        assertTrue(exists);

        boolean nonExistent = projectBlueprintRepository.existsById(UUID.randomUUID());
        assertFalse(nonExistent);
    }
} 