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
import pm.little.api.controllers.implementation.DaysApiController;
import pm.little.api.models.DayBlueprint;
import pm.little.courseservice.DayService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DaysControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DayService dayService;

    @InjectMocks
    private DaysApiController daysController;

    private DayBlueprint testDay;
    private UUID testDayUuid;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(daysController).build();

        testDayUuid = UUID.randomUUID();
        
        testDay = new DayBlueprint();
        testDay.setDayBlueprintUuid(testDayUuid);
        testDay.setTitle("Test Day");
        testDay.setDescription("Test Description");
    }

    @Test
    void testGetDay() throws Exception {
        when(dayService.getDayBlueprint(any())).thenReturn(testDay);

        mockMvc.perform(get("/days/{uuid}", testDayUuid)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Day"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void testCreateDay() throws Exception {
        when(dayService.createDayBlueprint(any())).thenReturn(testDay);

        mockMvc.perform(post("/days")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Test Day\",\"description\":\"Test Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Day"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void testUpdateDay() throws Exception {
        when(dayService.updateDayBlueprint(any(), any())).thenReturn(testDay);

        mockMvc.perform(put("/days/{uuid}", testDayUuid)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Day\",\"description\":\"Updated Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Day"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void testDeleteDay() throws Exception {
        mockMvc.perform(delete("/days/{uuid}", testDayUuid))
                .andExpect(status().isNoContent());
    }

    @Test
    void testListDays() throws Exception {
        List<DayBlueprint> days = Arrays.asList(testDay);
        when(dayService.getAllDayBlueprints(any(), any())).thenReturn(days);

        mockMvc.perform(get("/days")
                .param("limit", "10")
                .param("offset", "0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Day"))
                .andExpect(jsonPath("$[0].description").value("Test Description"));
    }
} 