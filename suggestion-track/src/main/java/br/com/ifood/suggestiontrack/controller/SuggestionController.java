package br.com.ifood.suggestiontrack.controller;

import br.com.ifood.suggestiontrack.models.spotify.Track;
import br.com.ifood.suggestiontrack.models.spotify.Tracks;
import br.com.ifood.suggestiontrack.models.spotify.mapper.TrackName;

import br.com.ifood.suggestiontrack.services.OpenWeatherService;
import br.com.ifood.suggestiontrack.services.SpotifyService;
import br.com.ifood.suggestiontrack.services.SuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class SuggestionController {

    private final SuggestService suggestService;
    private final OpenWeatherService openWeatherService;
    private final SpotifyService spotifyService;

    @Autowired
    public SuggestionController(SuggestService suggestService, OpenWeatherService openWeatherService,
                                SpotifyService spotifyService) {
        this.suggestService = suggestService;
        this.openWeatherService = openWeatherService;
        this.spotifyService = spotifyService;
    }

    @GetMapping("/{city}")
    public ResponseEntity<TrackName> getWeatherByCity(@PathVariable String city) {

        Tracks tracks = suggestService.suggerMusicByTemperatureCity(city);
        Float temperatureByCity = openWeatherService.getTemperatureByCity(city);


        List<String> namesMusic = new ArrayList<>();

        for (Track track : tracks.getTracksMusic()) {
            namesMusic.add(track.getName());
        }

        TrackName trackName = new TrackName();
        trackName.setTemperature(temperatureByCity);
        trackName.setGenre(spotifyService.obtainMusicalGenre(temperatureByCity));
        trackName.setName(namesMusic);
        trackName.setCity(city);

        return new ResponseEntity<>(trackName, HttpStatus.OK);
    }

}
