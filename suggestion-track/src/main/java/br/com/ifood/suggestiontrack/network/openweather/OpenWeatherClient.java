package br.com.ifood.suggestiontrack.network.openweather;

import br.com.ifood.suggestiontrack.error.CityNotFoundException;
import br.com.ifood.suggestiontrack.error.CoordinateWrongException;
import br.com.ifood.suggestiontrack.models.openweather.OpenWeather;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@FeignClient(name = "openWeatherClient", url = "${external.openWeather.url}", fallbackFactory = OpenWeatherClient.OpenWeatherClientFallback.class)
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

    /**
     * OpenWeather Fallback Class
     *
     * this class causes a fallback when the request returns response with value code status 0 in the Hystrix Value Code,
     * and a throws exception {@link CityNotFoundException CityNotFoundException}
     * when the error is 404 in response a Hystrix value.
     */
    @Slf4j
    @Component
    class OpenWeatherClientFallback implements FallbackFactory<OpenWeatherClient> {

        private static final Random RANDOM = new Random();

        @Override
        public OpenWeatherClient create(Throwable throwable) {
            return new OpenWeatherClient() {

                @Override
                public OpenWeather getOpenWeather(String city, String appId, String units) {
                    if (((FeignException) throwable).status() == 404) {
                        throw new CityNotFoundException();
                    }

                    return OpenWeatherClientFallback.this.getOpenWeather();
                }

                @Override
                public OpenWeather getOpenWeatherByCoordinates(Double lon, Double lat, String appId, String units) {
                    if (((FeignException) throwable).status() == 404) {
                        throw new CityNotFoundException();
                    } else if (((FeignException) throwable).status() == 400) {
                        throw new CoordinateWrongException();
                    }

                    return OpenWeatherClientFallback.this.getOpenWeather();
                }
            };
        }

        /**
         * Callback method for create a random float number to prevent the api from stopping,
         * releasing the spotify suggestion api to do its job.
         *
         * @return a random float number in class "Main" intro OpenWeather class number between 5ºC or 31ºC
         */
        private OpenWeather getOpenWeather() {
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
