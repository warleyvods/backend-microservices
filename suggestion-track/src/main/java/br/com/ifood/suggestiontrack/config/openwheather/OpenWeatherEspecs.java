package br.com.ifood.suggestiontrack.config.openwheather;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class OpenWeatherEspecs {

    @Value("${openweathermap.external.server.api}")
    private String url;

    @Value("${openweathermap.units}")
    private String units;

    @Value("${openweathermap.api.key}")
    private String appId;

}
