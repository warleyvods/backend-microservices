package br.com.ifood.suggestiontrack.controller;

import br.com.ifood.suggestiontrack.models.spotify.mapper.TrackName;
import br.com.ifood.suggestiontrack.services.SuggestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
     * This method is responsible for searching for a list of songs referring to the temperature of the city
     *
     * @param city city for the search
     * @return TrackName object with status code 200 or 404 for city not found.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "404", description = "When city not found!"),
    })
    @Operation(
            summary = "List all musics based on temperature city",
            description = "City for the search the temperature and search a list music based on temperature.",
            tags = "Suggestion Track Search Controller"
    )
    @GetMapping("/city")
    public ResponseEntity<TrackName> getSuggestMusicsByTemperatureCity(@RequestParam String city) {
        TrackName trackName = suggestService.suggestMusicByTemperatureCity(city);
        return new ResponseEntity<>(trackName, HttpStatus.OK);
    }

    /**
     * This method is responsible for searching for a list of songs related to the temperature
     * sought based on the latitude and longitude informed
     *
     * @param lat Latitude for the search. Minimum range (-90º) Maximum range (+90º)
     * @param lon Longitude for the search. Minimum range (-180) Maximum range (+180º)
     * @return TrackName object with status code 200 or 400 for bad requests.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "When coordinates are incorrect!"),
    })
    @Operation(
            summary = "List all musics based on temperature coordinates",
            description = "City for the search the temperature and search a list music based on coordinates.",
            tags = "Suggestion Track Search Controller"
    )
    @GetMapping("/coordinates")
    public ResponseEntity<TrackName> getSuggestMusicsByCoordinates(@RequestParam Double lat, @RequestParam Double lon) {
        TrackName trackName = suggestService.suggestMusicByCoordinates(lat, lon);
        return new ResponseEntity<>(trackName, HttpStatus.OK);
    }

}
