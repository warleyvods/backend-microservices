package br.com.ifood.suggestiontrack.services;

import br.com.ifood.suggestiontrack.enums.GenreMusic;
import br.com.ifood.suggestiontrack.models.spotify.Track;
import br.com.ifood.suggestiontrack.models.spotify.Tracks;
import br.com.ifood.suggestiontrack.models.spotify.mapper.TrackName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
    public TrackName suggestMusicByTemperatureCity(String city) {

        Float temperature = openWeatherService.getTemperatureByCity(city);
        GenreMusic genreMusic = spotifyService.obtainMusicalGenre(temperature);
        Tracks tracks = spotifyService.suggerMusicForGenre(genreMusic);

        List<String> namesMusic = new ArrayList<>();

        for (Track track : tracks.getTracksMusic()) {
            namesMusic.add(track.getName());
        }

        TrackName trackName = new TrackName();
        trackName.setTemperature(temperature);
        trackName.setGenre(genreMusic);
        trackName.setName(namesMusic);
        trackName.setCity(city);

        return trackName;
    }

    public TrackName suggestMusicByCoodinates(double latitude, double longitude) {

        Float temperature = openWeatherService.getTemperatureByGeographicCoordinates(latitude, longitude);
        GenreMusic genreMusic = spotifyService.obtainMusicalGenre(temperature);
        Tracks tracks = spotifyService.suggerMusicForGenre(genreMusic);

        List<String> namesMusic = new ArrayList<>();

        for (Track track : tracks.getTracksMusic()) {
            namesMusic.add(track.getName());
        }

        TrackName trackName = new TrackName();
        trackName.setTemperature(temperature);
        trackName.setGenre(genreMusic);
        trackName.setName(namesMusic);
        return trackName;
    }


}
