package br.com.ifood.suggestiontrack.services;


import br.com.ifood.suggestiontrack.config.openwheather.OpenWeatherConfig;
import br.com.ifood.suggestiontrack.network.openweather.OpenWeatherClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherService {

    private final OpenWeatherConfig openWeatherConfig;
    private final OpenWeatherClient openWeatherClient;

    public OpenWeatherService(OpenWeatherConfig openWeatherConfig, OpenWeatherClient openWeatherClient) {
        this.openWeatherConfig = openWeatherConfig;
        this.openWeatherClient = openWeatherClient;
    }

    /**
     * This method searches for the city temperature using the Open Weather API
     * using Spring Cloud Open Feign with Fallback.
     *
     * @param city that is researched the temperature.
     * @return city temperature in a float Celsius.
     */
    @Cacheable("temperatureByCity")
    public Float getTemperatureByCity(String city) {

        return openWeatherClient.getOpenWeather(city, openWeatherConfig.getAppId(), openWeatherConfig.getUnits())
                .getMain().getTemp();
    }

    /**
     * This method serches for the temperature city using geographic coordinates indicated.
     *
     * @param lat latitude for the search.
     * @param lon logitude for the search.
     * @return temperature of coordinates in a float number Celsius.
     */
    @Cacheable("temperatureByCoordinates")
    public Float getTemperatureByGeographicCoordinates(Double lat, Double lon) {
        return openWeatherClient.getOpenWeatherByCoordinates(lon, lat, openWeatherConfig.getAppId(), openWeatherConfig.getUnits()).getMain().getTemp();
    }

}
