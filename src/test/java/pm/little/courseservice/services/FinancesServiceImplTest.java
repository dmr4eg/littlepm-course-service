package pm.little.courseservice.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pm.little.api.models.Finances;
import pm.little.api.models.ids.FinancesId;
import pm.little.api.repositories.FinancesRepository;
import pm.little.courseservice.exceptions.FinancesNotFoundException;
import pm.little.courseservice.implementation.FinancesServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FinancesServiceImplTest {

    @Mock
    private FinancesRepository financesRepository;

    @InjectMocks
    private FinancesServiceImpl financesService;

    private UUID projectBlueprintUuid;
    private UUID userUuid;
    private FinancesId financesId;
    private Finances finances;

    @BeforeEach
    void setUp() {
        projectBlueprintUuid = UUID.randomUUID();
        userUuid = UUID.randomUUID();
        financesId = new FinancesId(projectBlueprintUuid, userUuid);
        
        finances = new Finances(financesId);
        finances.setInvestorGave(1000.0f);
        finances.setInvestorReturn(1200.0f);
        finances.setSpentBudget(800.0f);
        finances.setToysPlanned(100.0f);
        finances.setToysSold(80.0f);
        finances.setToysLeft(20.0f);
        finances.setMembersBudget(200.0f);
        finances.setProfit(200.0f);
        finances.setExpenseAmount(50.0f);
        finances.setCalculatedBudget(8.0f);
        finances.setItemsCost(400.0f);
        finances.setRecommendedPrice(15.0f);
        finances.setPricePerItem(12.0f);
        finances.setSoldPrice(14.0f);
    }

    @Test
    @DisplayName("Should get all finances")
    void shouldGetAllFinances() {
        List<Finances> financesList = Arrays.asList(finances);
        when(financesRepository.findAll()).thenReturn(financesList);

        List<Finances> result = financesService.getAllFinances();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(projectBlueprintUuid, result.get(0).getId().getProjectBlueprintUuid());
        assertEquals(userUuid, result.get(0).getId().getUserUuid());
    }

    @Test
    @DisplayName("Should get all finances with pagination")
    void shouldGetAllFinancesWithPagination() {
        List<Finances> financesList = Arrays.asList(finances);
        Pageable pageable = PageRequest.of(0, 10);
        
        when(financesRepository.findAll(pageable)).thenReturn(new PageImpl<>(financesList));

        List<Finances> result = financesService.getAllFinances(10, 0);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(projectBlueprintUuid, result.get(0).getId().getProjectBlueprintUuid());
    }

    @Test
    @DisplayName("Should get finances by ID")
    void shouldGetFinancesById() {
        when(financesRepository.findById(financesId)).thenReturn(Optional.of(finances));

        Finances result = financesService.getFinancesById(projectBlueprintUuid, userUuid);

        assertNotNull(result);
        assertEquals(projectBlueprintUuid, result.getId().getProjectBlueprintUuid());
        assertEquals(userUuid, result.getId().getUserUuid());
        assertEquals(1000.0f, result.getInvestorGave());
        assertEquals(1200.0f, result.getInvestorReturn());
    }

    @Test
    @DisplayName("Should throw exception when finances not found")
    void shouldThrowExceptionWhenFinancesNotFound() {
        when(financesRepository.findById(financesId)).thenReturn(Optional.empty());

        assertThrows(FinancesNotFoundException.class, () -> 
            financesService.getFinancesById(projectBlueprintUuid, userUuid));
    }

    @Test
    @DisplayName("Should create finances successfully")
    void shouldCreateFinances() {
        when(financesRepository.save(any(Finances.class))).thenReturn(finances);

        Finances result = financesService.createFinances(finances);

        assertNotNull(result);
        assertEquals(projectBlueprintUuid, result.getId().getProjectBlueprintUuid());
        assertEquals(userUuid, result.getId().getUserUuid());
        verify(financesRepository, times(1)).save(any(Finances.class));
    }

    @Test
    @DisplayName("Should update finances successfully")
    void shouldUpdateFinances() {
        Finances updatedFinances = new Finances(financesId);
        updatedFinances.setInvestorGave(1500.0f);
        updatedFinances.setInvestorReturn(1800.0f);
        updatedFinances.setSpentBudget(1200.0f);
        updatedFinances.setToysPlanned(120.0f);
        updatedFinances.setToysSold(100.0f);
        updatedFinances.setToysLeft(20.0f);
        updatedFinances.setMembersBudget(300.0f);
        updatedFinances.setProfit(300.0f);
        updatedFinances.setExpenseAmount(70.0f);
        updatedFinances.setCalculatedBudget(10.0f);
        updatedFinances.setItemsCost(600.0f);
        updatedFinances.setRecommendedPrice(18.0f);
        updatedFinances.setPricePerItem(15.0f);
        updatedFinances.setSoldPrice(17.0f);
        
        when(financesRepository.findById(financesId)).thenReturn(Optional.of(finances));
        when(financesRepository.save(any(Finances.class))).thenReturn(updatedFinances);

        Finances result = financesService.updateFinances(projectBlueprintUuid, userUuid, updatedFinances);

        assertNotNull(result);
        assertEquals(projectBlueprintUuid, result.getId().getProjectBlueprintUuid());
        assertEquals(userUuid, result.getId().getUserUuid());
        assertEquals(1500.0f, result.getInvestorGave());
        assertEquals(1800.0f, result.getInvestorReturn());
        assertEquals(1200.0f, result.getSpentBudget());
        assertEquals(120.0f, result.getToysPlanned());
        assertEquals(100.0f, result.getToysSold());
        assertEquals(20.0f, result.getToysLeft());
        assertEquals(300.0f, result.getMembersBudget());
        assertEquals(300.0f, result.getProfit());
        assertEquals(70.0f, result.getExpenseAmount());
        assertEquals(10.0f, result.getCalculatedBudget());
        assertEquals(600.0f, result.getItemsCost());
        assertEquals(18.0f, result.getRecommendedPrice());
        assertEquals(15.0f, result.getPricePerItem());
        assertEquals(17.0f, result.getSoldPrice());
        verify(financesRepository, times(1)).save(any(Finances.class));
    }

    @Test
    @DisplayName("Should delete finances successfully")
    void shouldDeleteFinances() {
        when(financesRepository.findById(financesId)).thenReturn(Optional.of(finances));
        doNothing().when(financesRepository).delete(finances);

        financesService.deleteFinances(projectBlueprintUuid, userUuid);

        verify(financesRepository, times(1)).delete(finances);
    }
} 