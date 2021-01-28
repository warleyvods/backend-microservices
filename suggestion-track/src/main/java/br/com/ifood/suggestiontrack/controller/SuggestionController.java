package br.com.ifood.suggestiontrack.controller;

import br.com.ifood.suggestiontrack.models.spotify.mapper.TrackName;
import br.com.ifood.suggestiontrack.models.spotify.Track;
import br.com.ifood.suggestiontrack.models.spotify.Tracks;
import br.com.ifood.suggestiontrack.services.OpenWeatherService;
import br.com.ifood.suggestiontrack.services.SpotifyService;
import br.com.ifood.suggestiontrack.services.SuggestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SuggestionController {

    private final SuggestService suggestService;
    private final OpenWeatherService openWeatherService;
    private final SpotifyService spotifyService;

    @GetMapping("/{city}")
    public TrackName getWeatherByCity(@PathVariable String city) {

        Tracks tracks = suggestService.suggerMusicByTemperatureCity(city);
        Float temperatureByCity = openWeatherService.getTemperatureByCity(city);


        List<String> namesMusic = new ArrayList<>();

        for (Track track : tracks.getTracks()) {
            namesMusic.add(track.getName());
        }

        TrackName trackName = new TrackName();
        trackName.setTemperature(temperatureByCity);
        trackName.setGenre(spotifyService.obtainMusicalGenre(temperatureByCity));
        trackName.setName(namesMusic);
        trackName.setCity(city);


        return trackName;
    }

}
