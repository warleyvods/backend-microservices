package br.com.ifood.suggestiontrack.webrequests;

import br.com.ifood.suggestiontrack.models.openweather.OpenWeather;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openweather", url = "${openweathermap.external.server.api}")
public interface OpenWeatherEndpoint {

    @RequestMapping(method = RequestMethod.GET)
    OpenWeather getOpenWeather(@RequestParam(value = "q") String city,
                               @RequestParam(value = "appid") String appId,
                               @RequestParam(value = "units") String units);

}
