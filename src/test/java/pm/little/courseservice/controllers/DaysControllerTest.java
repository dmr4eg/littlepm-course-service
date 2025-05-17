package pm.little.courseservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pm.little.api.controllers.implementation.DaysApiController;
import pm.little.api.models.DayBlueprint;
import pm.little.courseservice.DayService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
class DaysControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DayService dayService;

    @InjectMocks
    private DaysApiController daysController;

    private DayBlueprint testDay;
    private UUID testDayUuid;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(daysController).build();
        objectMapper = new ObjectMapper();
        
        testDayUuid = UUID.randomUUID();
        
        testDay = new DayBlueprint();
        testDay.setDayBlueprintUuid(testDayUuid);
        testDay.setTitle("Test Day");
        testDay.setDescription("Test Description");
        testDay.setText("Test Text Content");
    }

    @Test
    void testGetDay() throws Exception {
        when(dayService.getDayBlueprint(eq(testDayUuid))).thenReturn(testDay);

        mockMvc.perform(get("/days/{uuid}", testDayUuid)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Day"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void testCreateDay() throws Exception {
        when(dayService.createDayBlueprint(any(DayBlueprint.class))).thenReturn(testDay);

        mockMvc.perform(post("/days")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testDay)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Day"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void testUpdateDay() throws Exception {
        DayBlueprint updatedDay = new DayBlueprint();
        updatedDay.setDayBlueprintUuid(testDayUuid);
        updatedDay.setTitle("Updated Day");
        updatedDay.setDescription("Updated Description");
        updatedDay.setText("Updated Text");
        
        when(dayService.updateDayBlueprint(eq(testDayUuid), any(DayBlueprint.class))).thenReturn(testDay);

        mockMvc.perform(put("/days/{uuid}", testDayUuid)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDay)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Day"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void testDeleteDay() throws Exception {
        doNothing().when(dayService).deleteDayBlueprint(eq(testDayUuid));

        mockMvc.perform(delete("/days/{uuid}", testDayUuid))
                .andExpect(status().isNoContent());
    }

    @Test
    void testListDays() throws Exception {
        List<DayBlueprint> days = Arrays.asList(testDay);
        when(dayService.getAllDayBlueprints(eq(10), eq(0))).thenReturn(days);

        mockMvc.perform(get("/days")
                .param("limit", "10")
                .param("offset", "0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Day"))
                .andExpect(jsonPath("$[0].description").value("Test Description"));
    }
} 