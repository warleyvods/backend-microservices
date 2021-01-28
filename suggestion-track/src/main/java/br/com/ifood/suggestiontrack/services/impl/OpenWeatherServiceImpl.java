package br.com.ifood.suggestiontrack.services.impl;

import br.com.ifood.suggestiontrack.models.openweather.OpenWeather;
import br.com.ifood.suggestiontrack.properties.openwheather.OpenWeatherEspecs;
import br.com.ifood.suggestiontrack.services.OpenWeatherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OpenWeatherServiceImpl implements OpenWeatherService {

    private final OpenWeatherEspecs openWeatherEspecs;

    private final RestTemplate restTemplate;

    @Override
    public Float getTemperatureByCity(String cidade) {
        Float temperatura = null;

        try {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(openWeatherEspecs.getUrl());
            uriComponentsBuilder.queryParam("q", cidade);
            uriComponentsBuilder.queryParam("appid", openWeatherEspecs.getAppId());
            uriComponentsBuilder.queryParam("units", openWeatherEspecs.getUnits());

            OpenWeather OpenWeatherObjMap = restTemplate.getForObject(uriComponentsBuilder.build().toUriString(), OpenWeather.class);

            temperatura = Objects.requireNonNull(OpenWeatherObjMap).getMain().getTemp();


        } catch (HttpClientErrorException ex) {
            ex.getStackTrace();
        }
        return temperatura;
    }


}
