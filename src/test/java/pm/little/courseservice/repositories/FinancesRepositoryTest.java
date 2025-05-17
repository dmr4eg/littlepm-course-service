package pm.little.courseservice.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pm.little.api.models.Finances;
import pm.little.api.models.ids.FinancesId;
import pm.little.api.repositories.FinancesRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FinancesRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FinancesRepository financesRepository;

    @Test
    void testSaveFinances() {
        // Create test data
        UUID projectUuid = UUID.randomUUID();
        UUID userUuid = UUID.randomUUID();
        
        FinancesId financesId = new FinancesId();
        financesId.setProjectBlueprintUuid(projectUuid);
        financesId.setUserUuid(userUuid);

        Finances finances = new Finances();
        finances.setId(financesId);
        finances.setInvestorGave(100.0f);
        finances.setInvestorReturn(120.0f);

        // Save the finances
        Finances savedFinances = financesRepository.save(finances);

        // Verify the save
        assertNotNull(savedFinances);
        assertEquals(finances.getInvestorGave(), savedFinances.getInvestorGave());
        assertEquals(finances.getInvestorReturn(), savedFinances.getInvestorReturn());
    }

    @Test
    void testFindFinancesById() {
        // Create and persist test data
        UUID projectUuid = UUID.randomUUID();
        UUID userUuid = UUID.randomUUID();
        
        FinancesId financesId = new FinancesId();
        financesId.setProjectBlueprintUuid(projectUuid);
        financesId.setUserUuid(userUuid);

        Finances finances = new Finances();
        finances.setId(financesId);
        finances.setInvestorGave(100.0f);
        finances.setInvestorReturn(120.0f);

        entityManager.persist(finances);
        entityManager.flush();

        // Find the finances
        Optional<Finances> found = financesRepository.findById(financesId);

        // Verify the find
        assertTrue(found.isPresent());
        assertEquals(finances.getInvestorGave(), found.get().getInvestorGave());
        assertEquals(finances.getInvestorReturn(), found.get().getInvestorReturn());
    }

    @Test
    void testDeleteFinances() {
        // Create and persist test data
        UUID projectUuid = UUID.randomUUID();
        UUID userUuid = UUID.randomUUID();
        
        FinancesId financesId = new FinancesId();
        financesId.setProjectBlueprintUuid(projectUuid);
        financesId.setUserUuid(userUuid);

        Finances finances = new Finances();
        finances.setId(financesId);
        finances.setInvestorGave(100.0f);
        finances.setInvestorReturn(120.0f);

        entityManager.persist(finances);
        entityManager.flush();

        // Delete the finances
        financesRepository.delete(finances);

        // Verify the delete
        Optional<Finances> found = financesRepository.findById(financesId);
        assertFalse(found.isPresent());
    }

    @Test
    void testUpdateFinances() {
        // Create and persist test data
        UUID projectUuid = UUID.randomUUID();
        UUID userUuid = UUID.randomUUID();
        
        FinancesId financesId = new FinancesId();
        financesId.setProjectBlueprintUuid(projectUuid);
        financesId.setUserUuid(userUuid);

        Finances finances = new Finances();
        finances.setId(financesId);
        finances.setInvestorGave(100.0f);
        finances.setInvestorReturn(120.0f);

        entityManager.persist(finances);
        entityManager.flush();

        // Update the finances
        finances.setInvestorGave(150.0f);
        finances.setInvestorReturn(180.0f);
        financesRepository.save(finances);

        // Verify the update
        Optional<Finances> found = financesRepository.findById(financesId);
        assertTrue(found.isPresent());
        assertEquals(150.0f, found.get().getInvestorGave());
        assertEquals(180.0f, found.get().getInvestorReturn());
    }
} 