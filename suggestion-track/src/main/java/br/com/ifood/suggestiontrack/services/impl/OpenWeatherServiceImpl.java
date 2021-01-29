package br.com.ifood.suggestiontrack.services.impl;

import br.com.ifood.suggestiontrack.properties.openwheather.OpenWeatherEspecs;
import br.com.ifood.suggestiontrack.services.OpenWeatherService;
import br.com.ifood.suggestiontrack.webrequests.OpenWeatherEndpoint;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OpenWeatherServiceImpl implements OpenWeatherService {

    private final OpenWeatherEspecs openWeatherEspecs;

    private final OpenWeatherEndpoint openWeatherEndpoint;

    @Override
    public Float getTemperatureByCity(String city) {
        Float temperature = null;

        try {

            temperature = openWeatherEndpoint.getOpenWeather(city, openWeatherEspecs.getAppId(), openWeatherEspecs.getUnits())
                    .getMain().getTemp();

        } catch (HttpClientErrorException ex) {
            ex.getStackTrace();
        }
        return temperature;
    }


}
