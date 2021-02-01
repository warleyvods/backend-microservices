package br.com.ifood.suggestiontrack.network.openweather;

import br.com.ifood.suggestiontrack.models.openweather.OpenWeather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.util.Random;


@Primary
@FeignClient(name = "openWeatherClient", url = "${openweathermap.external.server.api}",
        fallback = OpenWeatherClient.OpenWeatherClientFallback.class)
public interface OpenWeatherClient {

    /**
     * This method uses open feign to return an object OpenWeather
     * that we will be able to obtain the temperature through the filling obtained by api.
     *
     * @param city  city for search temperature.
     * @param appId token for search in Open Weather API.
     * @param units units for parameter, use "metric" for (International System Of Units = Cº)
     *              or do not use nothing for default in Fº.
     *
     * @return object OpenWeather with filled parameters by OpenWeatherApi.
     */
    @GetMapping
    OpenWeather getOpenWeather(@RequestParam(value = "q") String city,
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
         *
         * @return a random float number in class "Main" intro OpenWeather class number between 5ºC or 31ºC
         */
        @Override
        public OpenWeather getOpenWeather(String city, String appId, String units) {
            log.debug("getOpenWeather in fallback!");

            float min = 5;
            float max = 31;

            float randomTemp = RANDOM.nextFloat() * (max - min) + min;

            OpenWeather.Main main = new OpenWeather.Main();
            main.setTemp(Float.parseFloat(new DecimalFormat("##.##").format(randomTemp)));

            OpenWeather openWeather = new OpenWeather();
            openWeather.setMain(main);

            return openWeather;
        }
    }
}