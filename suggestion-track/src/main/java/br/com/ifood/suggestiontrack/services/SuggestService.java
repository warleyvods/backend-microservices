package br.com.ifood.suggestiontrack.services;

import br.com.ifood.suggestiontrack.enums.GenreMusic;
import br.com.ifood.suggestiontrack.models.spotify.Tracks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SuggestService {

    private final SpotifyService spotifyService;
    private final OpenWeatherService openWeatherService;

    @Autowired
    public SuggestService(SpotifyService spotifyService, OpenWeatherService openWeatherService) {
        this.spotifyService = spotifyService;
        this.openWeatherService = openWeatherService;
    }

    /**
     * This method call OpenWeather API and Spotify API to
     * get the temperature and music suggestion data based on the genres.
     *
     * @param city city in search.
     * @return object Tracks filled.
     */
    public Tracks suggerMusicByTemperatureCity(String city) {

        Float temperature = openWeatherService.getTemperatureByCity(city);
        GenreMusic genreMusic = spotifyService.obtainMusicalGenre(temperature);

        return spotifyService.suggerMusicForGenre(genreMusic);
    }


}
