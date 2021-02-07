package br.com.ifood.suggestiontrack.services;

import br.com.ifood.suggestiontrack.enums.GenreMusic;
import br.com.ifood.suggestiontrack.error.CityNotFoundException;
import br.com.ifood.suggestiontrack.error.CoordinateWrongException;
import br.com.ifood.suggestiontrack.models.openweather.OpenWeather;
import br.com.ifood.suggestiontrack.models.spotify.Track;
import br.com.ifood.suggestiontrack.models.spotify.Tracks;
import br.com.ifood.suggestiontrack.models.spotify.mapper.TrackName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
        TrackName trackName = new TrackName();

        try {
            OpenWeather openWeatherResponse = openWeatherService.getTemperatureByCity(city);
            GenreMusic genreMusic = spotifyService.obtainMusicalGenre(openWeatherResponse.getMain().getTemp());
            Tracks tracks = spotifyService.suggerMusicForGenre(spotifyService.obtainMusicalGenre(openWeatherResponse.getMain().getTemp()));

            trackNameResponse(trackName, openWeatherResponse.getMain().getTemp(), genreMusic, tracks);
            trackName.setCity(openWeatherResponse.getName());

        } catch (RuntimeException runtimeEx) {
            throw new CityNotFoundException("City not found!");
        }

        return trackName;
    }

    private void trackNameResponse(TrackName trackName, Float temperature, GenreMusic genreMusic, Tracks tracks) {
        List<String> namesMusic = new ArrayList<>();

        for (Track track : tracks.getTracksMusic()) {
            namesMusic.add(track.getName());
        }

        trackName.setTemperature(temperature);
        trackName.setGenre(genreMusic);
        trackName.setName(namesMusic);
    }

    /**
     *
     * @param latitude latitude min -90 max +90 degrees
     * @param longitude longitude min -180 max +180 degrees
     * @return
     * @throws CoordinateWrongException if range are invalid.
     */
    public TrackName suggestMusicByCoordinates(double latitude, double longitude) {
        TrackName trackName = new TrackName();
        try {
            OpenWeather openWeatherResponse = openWeatherService.getTemperatureByGeographicCoordinates(latitude, longitude);
            GenreMusic genreMusic = spotifyService.obtainMusicalGenre(openWeatherResponse.getMain().getTemp());
            Tracks tracks = spotifyService.suggerMusicForGenre(genreMusic);

            trackNameResponse(trackName, openWeatherResponse.getMain().getTemp(), genreMusic, tracks);
            trackName.setCity(openWeatherResponse.getName());

        } catch (RuntimeException runtimeEx) {
            throw new CoordinateWrongException("Wrong value for Coordinates!");
        }

        return trackName;
    }

}
