package br.com.ifood.suggestiontrack.controller;

import br.com.ifood.suggestiontrack.services.SuggestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SuggestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @InjectMocks
//    SuggestionController suggestionController;

//    @Mock
//    SuggestService suggestService;

//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }



    @Test
    public void getSuggestMusicsByTemperatureCity() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/city").param("city", "Cidade"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(hasKey("genre")))
                .andExpect(jsonPath("$").value(hasKey("temperature")))
                .andExpect(jsonPath("$").value(hasKey("city")))
                .andExpect(jsonPath("$").value(hasKey("name")));
        System.out.println(resultActions);
    }

    @Test
    public void getSuggestMusicsByCoordinates() {
    }
}
