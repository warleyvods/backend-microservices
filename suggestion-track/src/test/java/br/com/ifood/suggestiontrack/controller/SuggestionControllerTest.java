package br.com.ifood.suggestiontrack.controller;

import br.com.ifood.suggestiontrack.models.spotify.mapper.TrackName;
import br.com.ifood.suggestiontrack.services.SuggestService;
import br.com.ifood.suggestiontrack.util.TrackNameMockCreator;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.hasKey;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
public class SuggestionControllerTest {

//    @Autowired
//    private MockMvc mockMvc;

    @InjectMocks
    SuggestionController suggestionController;

    @Mock
    SuggestService suggestServiceMock;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(suggestServiceMock.suggestMusicByTemperatureCity(anyString())).thenReturn(TrackNameMockCreator.createTrackNameResponse());
    }

    @Test
    @DisplayName("When write a city in request param return a TrackName Object")
    public void test() throws Exception {
        List<String> expectedName = TrackNameMockCreator.createTrackNameResponse().getMusicNames();
        TrackName bodyResponse = suggestionController.getSuggestMusicsByTemperatureCity(anyString()).getBody();
        assertThat(bodyResponse.getMusicNames()).isEqualTo(expectedName);
    }



//    @Test
//    public void getSuggestMusicsByTemperatureCity() throws Exception {
//        ResultActions resultActions = mockMvc.perform(get("/city").param("city", "Cidade"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").value(hasKey("genre")))
//                .andExpect(jsonPath("$").value(hasKey("temperature")))
//                .andExpect(jsonPath("$").value(hasKey("city")))
//                .andExpect(jsonPath("$").value(hasKey("name")));
//        System.out.println(resultActions);
//    }

//    @Test
//    public void getSuggestMusicsByCoordinates() {
//    }
}
