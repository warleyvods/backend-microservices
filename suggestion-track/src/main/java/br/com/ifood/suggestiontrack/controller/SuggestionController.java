package br.com.ifood.suggestiontrack.controller;

import br.com.ifood.suggestiontrack.services.OpenWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SuggestionController {

    private final OpenWeatherService openWeatherService;

    @GetMapping("/{city}")
    public Float getWeatherByCity(@PathVariable String city) {
        return openWeatherService.getTemperatureByCity(city);
    }

}
