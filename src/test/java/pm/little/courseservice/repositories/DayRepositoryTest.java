package pm.little.courseservice.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import pm.little.api.models.DayBlueprint;
import pm.little.api.repositories.DayBlueprintRepository;
import pm.little.courseservice.TestConfig;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(TestConfig.class)
@ActiveProfiles("test")
class DayRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DayBlueprintRepository dayRepository;

    @Test
    void testSaveDay() {
        // Create test data
        DayBlueprint day = new DayBlueprint();
        day.setTitle("Test Day");
        day.setDescription("Test Description");
        day.setText("Test Text");

        // Save the day
        DayBlueprint savedDay = dayRepository.save(day);

        // Verify the save
        assertNotNull(savedDay);
        assertNotNull(savedDay.getDayBlueprintUuid());
        assertEquals(day.getTitle(), savedDay.getTitle());
        assertEquals(day.getDescription(), savedDay.getDescription());
    }

    @Test
    void testFindDayById() {
        // Create and persist test data
        DayBlueprint day = new DayBlueprint();
        day.setTitle("Test Day");
        day.setDescription("Test Description");
        day.setText("Test Text");

        entityManager.persist(day);
        entityManager.flush();

        // Find the day
        Optional<DayBlueprint> found = dayRepository.findById(day.getDayBlueprintUuid());

        // Verify the find
        assertTrue(found.isPresent());
        assertEquals(day.getTitle(), found.get().getTitle());
        assertEquals(day.getDescription(), found.get().getDescription());
    }

    @Test
    void testDeleteDay() {
        // Create and persist test data
        DayBlueprint day = new DayBlueprint();
        day.setTitle("Test Day");
        day.setDescription("Test Description");
        day.setText("Test Text");

        entityManager.persist(day);
        entityManager.flush();

        // Delete the day
        dayRepository.delete(day);

        // Verify the delete
        Optional<DayBlueprint> found = dayRepository.findById(day.getDayBlueprintUuid());
        assertFalse(found.isPresent());
    }

    @Test
    void testUpdateDay() {
        // Create and persist test data
        DayBlueprint day = new DayBlueprint();
        day.setTitle("Test Day");
        day.setDescription("Test Description");
        day.setText("Test Text");

        entityManager.persist(day);
        entityManager.flush();

        // Update the day
        day.setTitle("Updated Day");
        day.setDescription("Updated Description");
        dayRepository.save(day);

        // Verify the update
        Optional<DayBlueprint> found = dayRepository.findById(day.getDayBlueprintUuid());
        assertTrue(found.isPresent());
        assertEquals("Updated Day", found.get().getTitle());
        assertEquals("Updated Description", found.get().getDescription());
    }

    @Test
    void testFindAllDays() {
        // Create and persist test data
        DayBlueprint day1 = new DayBlueprint();
        day1.setTitle("Day 1");
        day1.setDescription("Description 1");
        day1.setText("Text 1");

        DayBlueprint day2 = new DayBlueprint();
        day2.setTitle("Day 2");
        day2.setDescription("Description 2");
        day2.setText("Text 2");

        entityManager.persist(day1);
        entityManager.persist(day2);
        entityManager.flush();

        // Find all days
        var days = dayRepository.findAll();

        // Verify the find
        assertEquals(2, days.size());
        assertTrue(days.stream().anyMatch(d -> d.getTitle().equals("Day 1")));
        assertTrue(days.stream().anyMatch(d -> d.getTitle().equals("Day 2")));
    }
} 