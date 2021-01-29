package br.com.ifood.suggestiontrack.webrequests.fallbacks;

import br.com.ifood.suggestiontrack.models.openweather.Main;
import br.com.ifood.suggestiontrack.models.openweather.OpenWeather;
import br.com.ifood.suggestiontrack.webrequests.OpenWeatherEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OpenWeatherEndpointFallback implements OpenWeatherEndpoint {

    @Override
    public OpenWeather getOpenWeather(String city, String appId, String units) {
        log.info("fallback intro");

        Main m = new Main();
        m.setTemp(25F);

        OpenWeather openWeather = new OpenWeather();
        openWeather.setMain(m);

        return openWeather;
    }
}
