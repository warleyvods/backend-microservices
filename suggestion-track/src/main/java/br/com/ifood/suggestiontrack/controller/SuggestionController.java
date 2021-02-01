package br.com.ifood.suggestiontrack.controller;

import br.com.ifood.suggestiontrack.models.spotify.mapper.TrackName;
import br.com.ifood.suggestiontrack.services.SuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/")
public class SuggestionController {

    private final SuggestService suggestService;

    @Autowired
    public SuggestionController(SuggestService suggestService) {
        this.suggestService = suggestService;
    }

    /**
     *
     * @param city
     * @return
     */
    @GetMapping("/city")
    public ResponseEntity<TrackName> getSuggestMusicsByTemperatureCity(@RequestParam String city) {
        TrackName trackName = suggestService.suggestMusicByTemperatureCity(city);
        return new ResponseEntity<>(trackName, HttpStatus.OK);
    }

    /**
     *
     * @param lat
     * @param lon
     * @return
     */
    @GetMapping("/coordinates")
    public ResponseEntity<TrackName> getSuggestMusicsByCoordinates(@RequestParam Double lat, @RequestParam Double lon) {
        TrackName trackName = suggestService.suggestMusicByCoodinates(lat, lon);
        return new ResponseEntity<>(trackName, HttpStatus.OK);
    }

}
