package pm.little.courseservice.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import pm.little.api.models.ProjectBlueprint;
import pm.little.api.models.enums.DifficultyEnum;
import pm.little.api.models.enums.StyleEnum;
import pm.little.api.repositories.ProjectBlueprintRepository;
import pm.little.courseservice.TestConfig;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(TestConfig.class)
@ActiveProfiles("test")
class ProjectRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProjectBlueprintRepository projectRepository;

    @Test
    void testSaveProject() {
        // Create test data
        ProjectBlueprint project = new ProjectBlueprint();
        project.setTitle("Test Project");
        project.setDescription("Test Description");
        project.setDifficulty(DifficultyEnum.EASY);
        project.setStyle(StyleEnum.DIY);
        project.setPosterUrl("https://example.com/poster.jpg");
        project.setWelcomeVideoUrl("https://example.com/welcome.mp4");

        // Save the project
        ProjectBlueprint savedProject = projectRepository.save(project);

        // Verify the save
        assertNotNull(savedProject);
        assertNotNull(savedProject.getProjectBlueprintUuid());
        assertEquals(project.getTitle(), savedProject.getTitle());
        assertEquals(project.getDescription(), savedProject.getDescription());
    }

    @Test
    void testFindProjectById() {
        // Create and persist test data
        ProjectBlueprint project = new ProjectBlueprint();
        project.setTitle("Test Project");
        project.setDescription("Test Description");
        project.setDifficulty(DifficultyEnum.EASY);
        project.setStyle(StyleEnum.DIY);
        project.setPosterUrl("https://example.com/poster.jpg");
        project.setWelcomeVideoUrl("https://example.com/welcome.mp4");

        entityManager.persist(project);
        entityManager.flush();

        // Find the project
        Optional<ProjectBlueprint> found = projectRepository.findById(project.getProjectBlueprintUuid());

        // Verify the find
        assertTrue(found.isPresent());
        assertEquals(project.getTitle(), found.get().getTitle());
        assertEquals(project.getDescription(), found.get().getDescription());
    }

    @Test
    void testDeleteProject() {
        // Create and persist test data
        ProjectBlueprint project = new ProjectBlueprint();
        project.setTitle("Test Project");
        project.setDescription("Test Description");
        project.setDifficulty(DifficultyEnum.EASY);
        project.setStyle(StyleEnum.DIY);
        project.setPosterUrl("https://example.com/poster.jpg");
        project.setWelcomeVideoUrl("https://example.com/welcome.mp4");

        entityManager.persist(project);
        entityManager.flush();

        // Delete the project
        projectRepository.delete(project);

        // Verify the delete
        Optional<ProjectBlueprint> found = projectRepository.findById(project.getProjectBlueprintUuid());
        assertFalse(found.isPresent());
    }

    @Test
    void testUpdateProject() {
        // Create and persist test data
        ProjectBlueprint project = new ProjectBlueprint();
        project.setTitle("Test Project");
        project.setDescription("Test Description");
        project.setDifficulty(DifficultyEnum.EASY);
        project.setStyle(StyleEnum.DIY);
        project.setPosterUrl("https://example.com/poster.jpg");
        project.setWelcomeVideoUrl("https://example.com/welcome.mp4");

        entityManager.persist(project);
        entityManager.flush();

        // Update the project
        project.setTitle("Updated Project");
        project.setDescription("Updated Description");
        projectRepository.save(project);

        // Verify the update
        Optional<ProjectBlueprint> found = projectRepository.findById(project.getProjectBlueprintUuid());
        assertTrue(found.isPresent());
        assertEquals("Updated Project", found.get().getTitle());
        assertEquals("Updated Description", found.get().getDescription());
    }

    @Test
    void testFindAllProjects() {
        // Create and persist test data
        ProjectBlueprint project1 = new ProjectBlueprint();
        project1.setTitle("Project 1");
        project1.setDescription("Description 1");
        project1.setDifficulty(DifficultyEnum.EASY);
        project1.setStyle(StyleEnum.DIY);
        project1.setPosterUrl("https://example.com/poster1.jpg");
        project1.setWelcomeVideoUrl("https://example.com/welcome1.mp4");

        ProjectBlueprint project2 = new ProjectBlueprint();
        project2.setTitle("Project 2");
        project2.setDescription("Description 2");
        project2.setDifficulty(DifficultyEnum.MEDIUM);
        project2.setStyle(StyleEnum.READY_MADE);
        project2.setPosterUrl("https://example.com/poster2.jpg");
        project2.setWelcomeVideoUrl("https://example.com/welcome2.mp4");

        entityManager.persist(project1);
        entityManager.persist(project2);
        entityManager.flush();

        // Find all projects
        var projects = projectRepository.findAll();

        // Verify the find
        assertEquals(2, projects.size());
        assertTrue(projects.stream().anyMatch(p -> p.getTitle().equals("Project 1")));
        assertTrue(projects.stream().anyMatch(p -> p.getTitle().equals("Project 2")));
    }
} 