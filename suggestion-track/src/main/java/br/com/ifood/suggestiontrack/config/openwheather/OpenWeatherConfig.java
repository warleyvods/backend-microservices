package br.com.ifood.suggestiontrack.config.openwheather;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class loads the variables responsible for configuring the api call Open Weather
 * using Spring Cloud Config.
 * @author Warley Vinicius
 */
@Getter
@Component
public class OpenWeatherConfig {

    @Value("${external.openWeather.url}")
    private String url;

    @Value("${external.openWeather.units}")
    private String units;

    @Value("${external.openWeather.appId}")
    private String appId;

}
