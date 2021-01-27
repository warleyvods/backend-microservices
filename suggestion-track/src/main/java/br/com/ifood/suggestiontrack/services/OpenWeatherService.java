package br.com.ifood.suggestiontrack.services;

import br.com.ifood.suggestiontrack.properties.openwheather.OpenWeatherEspecs;
import br.com.ifood.suggestiontrack.models.openweather.OpenWeather;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * @author Warley Vinicius
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OpenWeatherService {

    private final OpenWeatherEspecs openWeatherEspecs;

    private final RestTemplate restTemplate;

    public Float getTemperatureByCity(String cidade) {
        Float temperatura = null;

        try {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(openWeatherEspecs.getUrl());
            uriComponentsBuilder.queryParam("q", cidade);
            uriComponentsBuilder.queryParam("appid", openWeatherEspecs.getAppId());

            OpenWeather OpenWeatherObjMap = restTemplate.getForObject(uriComponentsBuilder.build().toUriString(), OpenWeather.class);

            temperatura = Objects.requireNonNull(OpenWeatherObjMap).getMain().getTemp();

        } catch (HttpClientErrorException ex) {
            ex.getStackTrace();
        }
        return temperatura;
    }

    private String convertToCelsius(Float tempString) {
        return new BigDecimal(tempString).subtract(BigDecimal.valueOf(273.15))
                .setScale(0, RoundingMode.CEILING).toString();

    }


}
