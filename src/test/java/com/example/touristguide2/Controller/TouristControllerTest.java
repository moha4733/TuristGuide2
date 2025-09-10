package com.example.touristguide2.Controller;

import com.example.touristguide2.Model.TouristAttraction;
import com.example.touristguide2.Service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TouristController.class)
class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService turistService;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldReturnAllAttractionsAsJson() throws Exception {
        when(touristService.getAllAttractions()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void shouldShowAddForm() throws Exception {
        mockMvc.perform(get("/attractions/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addAttraction")); // matcher controlleren
    }

    @Test
    void shouldSaveAttractionAndRedirect() throws Exception {
        TouristAttraction attraction = new TouristAttraction();
        when(touristService.saveAttraction(attraction)).thenReturn(attraction);

        mockMvc.perform(post("/attractions/save")
                        .flashAttr("attraction", attraction))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions"));

        verify(touristService).saveAttraction(attraction);
    }
}
