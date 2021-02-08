package br.com.ifood.suggestiontrack.services;

import br.com.ifood.suggestiontrack.SuggestionTrackApplication;
import br.com.ifood.suggestiontrack.config.mocks.OpenWeatherMocks;
import br.com.ifood.suggestiontrack.models.openweather.OpenWeather;
import br.com.ifood.suggestiontrack.network.openweather.OpenWeatherClient;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuggestionTrackApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OpenWeatherServiceTest {

    @Autowired
    private WireMockServer openWeatherService;

    @Autowired
    private OpenWeatherClient openWeatherClient;


    @BeforeEach
    void setUp() throws IOException {
        OpenWeatherMocks.setupOpenWeatherMocksResponse(openWeatherService);
    }

    @Test
    public void naoBuscarOpenWeather() {
        OpenWeather openWeather = openWeatherClient.getOpenWeather("string", "string", "string");
        assertThat(openWeather.getName()).isEqualTo("Goiânia");
        assertThat(openWeather.getMain().getTemp()).isEqualTo(31F);
    }

    @Test
    public void naoBuscarSemCidadeEApresentar400BacRequest() {
        OpenWeather openWeather = openWeatherClient.getOpenWeather(null, "string", "string");
        System.out.println(openWeather);
    }

}
