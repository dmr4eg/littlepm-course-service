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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(financesController).build();

        projectUuid = UUID.randomUUID();
        userUuid = UUID.randomUUID();
        
        FinancesId financesId = new FinancesId(projectUuid, userUuid);
        testFinances = new Finances(financesId);
        testFinances.setInvestorGave(1000.0f);
        testFinances.setInvestorReturn(1200.0f);
    }

    @Test
    void testGetFinances() throws Exception {
        when(financesService.getFinancesById(any(), any())).thenReturn(testFinances);

        mockMvc.perform(get("/finances/{project_uuid}/{user_uuid}", projectUuid, userUuid)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.investor_gave").value(1000.0))
                .andExpect(jsonPath("$.investor_return").value(1200.0));
    }

    @Test
    void testCreateFinances() throws Exception {
        when(financesService.createFinances(any())).thenReturn(testFinances);

        mockMvc.perform(post("/finances")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":{\"project_blueprint_uuid\":\"" + projectUuid + "\",\"user_uuid\":\"" + userUuid + "\"},\"investor_gave\":1000,\"investor_return\":1200}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.investor_gave").value(1000.0))
                .andExpect(jsonPath("$.investor_return").value(1200.0));
    }

    @Test
    void testUpdateFinances() throws Exception {
        when(financesService.updateFinances(any(), any(), any())).thenReturn(testFinances);

        mockMvc.perform(put("/finances/{project_uuid}/{user_uuid}", projectUuid, userUuid)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":{\"project_blueprint_uuid\":\"" + projectUuid + "\",\"user_uuid\":\"" + userUuid + "\"},\"investor_gave\":1500,\"investor_return\":1800}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.investor_gave").value(1000.0))
                .andExpect(jsonPath("$.investor_return").value(1200.0));
    }

    @Test
    void testDeleteFinances() throws Exception {
        mockMvc.perform(delete("/finances/{project_uuid}/{user_uuid}", projectUuid, userUuid))
                .andExpect(status().isNoContent());
    }

    @Test
    void testListFinances() throws Exception {
        List<Finances> finances = Arrays.asList(testFinances);
        when(financesService.getAllFinances(any(), any())).thenReturn(finances);

        mockMvc.perform(get("/finances")
                .param("limit", "10")
                .param("offset", "0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].investor_gave").value(1000.0))
                .andExpect(jsonPath("$[0].investor_return").value(1200.0));
    }
} 