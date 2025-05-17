package pm.little.courseservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pm.little.api.controllers.implementation.FinancesApiController;
import pm.little.api.models.Finances;
import pm.little.api.models.ids.FinancesId;
import pm.little.courseservice.FinancesService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class FinancesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FinancesService financesService;

    @InjectMocks
    private FinancesApiController financesController;

    private UUID projectUuid;
    private UUID userUuid;
    private Finances testFinances;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(financesController).build();
        objectMapper = new ObjectMapper();

        projectUuid = UUID.randomUUID();
        userUuid = UUID.randomUUID();
        
        FinancesId financesId = new FinancesId(projectUuid, userUuid);
        testFinances = new Finances(financesId);
        testFinances.setInvestorGave(1000.0f);
        testFinances.setInvestorReturn(1200.0f);
        // Set all required fields for validation
        testFinances.setSpentBudget(800.0f);
        testFinances.setToysLeft(10.0f);
        testFinances.setMembersBudget(900.0f);
        testFinances.setToysPlanned(100.0f);
        testFinances.setItemsCost(50.0f);
        testFinances.setCalculatedBudget(5000.0f);
        testFinances.setToysSold(90.0f);
        testFinances.setRecommendedPrice(55.0f);
        testFinances.setExpenseAmount(4500.0f);
        testFinances.setProfit(500.0f);
        testFinances.setPricePerItem(45.0f);
        testFinances.setSoldPrice(4050.0f);
    }

    @Test
    void testGetFinances() throws Exception {
        when(financesService.getFinancesById(eq(projectUuid), eq(userUuid))).thenReturn(testFinances);

        mockMvc.perform(get("/finances/{project_uuid}/{user_uuid}", projectUuid, userUuid)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.investor_gave").value(1000.0))
                .andExpect(jsonPath("$.investor_return").value(1200.0));
    }

    @Test
    void testCreateFinances() throws Exception {
        when(financesService.createFinances(any(Finances.class))).thenReturn(testFinances);

        mockMvc.perform(post("/finances")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testFinances)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.investor_gave").value(1000.0))
                .andExpect(jsonPath("$.investor_return").value(1200.0));
    }

    @Test
    void testUpdateFinances() throws Exception {
        when(financesService.updateFinances(eq(projectUuid), eq(userUuid), any(Finances.class))).thenReturn(testFinances);

        mockMvc.perform(put("/finances/{project_uuid}/{user_uuid}", projectUuid, userUuid)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testFinances)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.investor_gave").value(1000.0))
                .andExpect(jsonPath("$.investor_return").value(1200.0));
    }

    @Test
    void testDeleteFinances() throws Exception {
        doNothing().when(financesService).deleteFinances(eq(projectUuid), eq(userUuid));

        mockMvc.perform(delete("/finances/{project_uuid}/{user_uuid}", projectUuid, userUuid))
                .andExpect(status().isNoContent());
    }

    @Test
    void testListFinances() throws Exception {
        List<Finances> finances = Arrays.asList(testFinances);
        when(financesService.getAllFinances(eq(10), eq(0))).thenReturn(finances);

        mockMvc.perform(get("/finances")
                .param("limit", "10")
                .param("offset", "0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].investor_gave").value(1000.0))
                .andExpect(jsonPath("$[0].investor_return").value(1200.0));
    }
} 