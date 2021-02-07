package br.com.ifood.suggestiontrack.controller;

import br.com.ifood.suggestiontrack.models.spotify.mapper.TrackName;
import br.com.ifood.suggestiontrack.services.SuggestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




/**
 * Suggestion Tracks Endpoints
 */
@RestController
public class SuggestionController {

    private final SuggestService suggestService;

    public SuggestionController(SuggestService suggestService) {
        this.suggestService = suggestService;
    }

    /**
     * @param city city for the search
     * @return TrackName object with status code 200
     */
    @GetMapping("/city")
    public ResponseEntity<TrackName> getSuggestMusicsByTemperatureCity(@RequestParam  String city) {
        TrackName trackName = suggestService.suggestMusicByTemperatureCity(city);
        return new ResponseEntity<>(trackName, HttpStatus.OK);
    }

    /**
     * @param lat
     * @param lon
     * @return
     */
    @GetMapping("/coordinates")
    public ResponseEntity<TrackName> getSuggestMusicsByCoordinates(@RequestParam Double lat, @RequestParam Double lon) {
        TrackName trackName = suggestService.suggestMusicByCoordinates(lat, lon);
        return new ResponseEntity<>(trackName, HttpStatus.OK);
    }

}
