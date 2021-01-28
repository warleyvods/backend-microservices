package br.com.ifood.suggestiontrack.services.impl;

import br.com.ifood.suggestiontrack.enums.GenreMusic;
import br.com.ifood.suggestiontrack.models.spotify.Tracks;
import br.com.ifood.suggestiontrack.services.OpenWeatherService;
import br.com.ifood.suggestiontrack.services.SpotifyService;
import br.com.ifood.suggestiontrack.services.SuggestService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SuggestServiceImpl implements SuggestService {

    private final SpotifyService spotifyService;
    private final OpenWeatherService openWeatherService;


    @Override
    public Tracks suggerMusicByTemperatureCity(String city) {

        Float temperature = openWeatherService.getTemperatureByCity(city);

        GenreMusic genreMusic = spotifyService.obtainMusicalGenre(temperature);

        Tracks tracks = spotifyService.suggerMusicForGenre(genreMusic);

        return tracks;
    }


}
