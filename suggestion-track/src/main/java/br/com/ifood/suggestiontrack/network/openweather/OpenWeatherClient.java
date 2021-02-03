package br.com.ifood.suggestiontrack.network.openweather;

import br.com.ifood.suggestiontrack.models.openweather.OpenWeather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

//TODO
@FeignClient(name = "openWeatherClient", url = "${external.openWeather.url}",
        fallback = OpenWeatherClient.OpenWeatherClientFallback.class, primary = true)
public interface OpenWeatherClient {

    /**
     * This method uses open feign to return an object OpenWeather
     * that we will be able to obtain the temperature through the filling obtained by api.
     *
     * @param city  city for search temperature.
     * @param appId token for search in Open Weather API.
     * @param units units for parameter, use "metric" for (International System Of Units = Cº)
     *              or do not use nothing for default in Fº.
     * @return object OpenWeather with filled parameters by OpenWeatherApi.
     */
    @GetMapping
    OpenWeather getOpenWeather(@RequestParam(value = "q") String city,
                               @RequestParam(value = "appid") String appId,
                               @RequestParam(value = "units") String units);

    /**
     * This method uses Spring Cloud Open Feign to return an object OpenWeather filled with the temperature based on
     * geographic coordinates.
     *
     * @param lon   longitude for search
     * @param lat   latitude for search
     * @param appId token for search in Open Weather API.
     * @param units units for parameter, use "metric" for (International System Of Units = ºC)
     *              or do not use nothing for default in ºF.
     * @return a random float number in class "Main" intro OpenWeather class number between 5ºC or 31ºC
     */
    @GetMapping
    OpenWeather getOpenWeatherByCoordinates(@RequestParam(value = "lon") Double lon,
                                            @RequestParam(value = "lat") Double lat,
                                            @RequestParam(value = "appid") String appId,
                                            @RequestParam(value = "units") String units);

    @Slf4j
    @Component
    class OpenWeatherClientFallback implements OpenWeatherClient {

        private static final Random RANDOM = new Random();

        /**
         * Callback method for create a random float number to prevent the api from stopping,
         * releasing the spotify suggestion api to do its job.
         *
         * @param city  city for search temperature.
         * @param appId token for search in Open Weather API.
         * @param units units for parameter, use "metric" for (International System Of Units = ºC)
         *              or do not use nothing for default in ºF.
         * @return a random float number in class "Main" intro OpenWeather class number between 5ºC or 31ºC
         */
        @Override
        public OpenWeather getOpenWeather(String city, String appId, String units) {
            log.debug("getOpenWeather in fallback!");

            float min = 5;
            float max = 31;

            float randomTemp = RANDOM.nextFloat() * (max - min) + min;

            OpenWeather.Main main = new OpenWeather.Main();
            main.setTemp(randomTemp);

            OpenWeather openWeather = new OpenWeather();
            openWeather.setMain(main);

            return openWeather;
        }

        /**
         * Callback method for create a random float number to prevent the api from stopping,
         * releasing the spotify suggestion api to do its job.
         *
         * @param lon   longitude for search
         * @param lat   latitude for search
         * @param appId token for search in Open Weather API.
         * @param units units for parameter, use "metric" for (International System Of Units = ºC)
         *              or do not use nothing for default in ºF.
         * @return a random float number in class "Main" intro OpenWeather class number between 5ºC or 31ºC
         */
        @Override
        public OpenWeather getOpenWeatherByCoordinates(Double lon, Double lat, String appId, String units) {
            log.debug("getOpenWeather in fallback!");

            float min = 5;
            float max = 31;

            float randomTemp = RANDOM.nextFloat() * (max - min) + min;

            OpenWeather.Main main = new OpenWeather.Main();
            main.setTemp(randomTemp);

            OpenWeather openWeather = new OpenWeather();
            openWeather.setMain(main);

            return openWeather;
        }
    }
}
