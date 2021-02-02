package br.com.ifood.suggestiontrack.controller;

import br.com.ifood.suggestiontrack.models.spotify.mapper.TrackName;
import br.com.ifood.suggestiontrack.services.SuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
public class SuggestionController {

    private final SuggestService suggestService;

    public SuggestionController(SuggestService suggestService) {
        this.suggestService = suggestService;
    }

    /**
     *
     * @param city city for the search
     * @return TrackName object with status code 200
     */
    @GetMapping("/{city}")
    public ResponseEntity<TrackName> getSuggestMusicsByTemperatureCity(@PathVariable String city) {
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
