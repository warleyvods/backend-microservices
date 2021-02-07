package br.com.ifood.suggestiontrack.services;

import br.com.ifood.suggestiontrack.config.spotify.SpotifySpecs;
import br.com.ifood.suggestiontrack.config.HttpConfig;
import br.com.ifood.suggestiontrack.enums.GenreMusic;
import br.com.ifood.suggestiontrack.models.spotify.OauthTokenResponse;
import br.com.ifood.suggestiontrack.models.spotify.Tracks;
import br.com.ifood.suggestiontrack.network.spotify.SpotifyRecommendationClient;
import br.com.ifood.suggestiontrack.network.spotify.SpotifyTokenClient;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class SpotifyService {

    private final SpotifySpecs spotifySpecs;
    private final SpotifyTokenClient spotifyTokenClient;
    private final SpotifyRecommendationClient spotifyRecommendationClient;

    public SpotifyService(SpotifySpecs spotifySpecs, SpotifyTokenClient spotifyTokenClient, SpotifyRecommendationClient spotifyRecommendationClient) {
        this.spotifySpecs = spotifySpecs;
        this.spotifyTokenClient = spotifyTokenClient;
        this.spotifyRecommendationClient = spotifyRecommendationClient;
    }

    /**
     * This method performs the call to obtain the token and adds the token together with the genre
     * to obtain the recommendations of the songs based on the described genre.
     *
     * @param genre genre described based on temperature.
     * @return object tracks filled.
     */
    public Tracks suggerMusicForGenre(GenreMusic genre) {
        String oAuth = HttpConfig.BEARER_AUTH + getApiKeyFeign().getAccessToken();

        return spotifyRecommendationClient.suggestMusicForGenre(genre.toString(), oAuth);
    }

    /**
     * Conditional struct for chose a genre by the temperature
     *
     * @param temperature temperature from the city
     * @return enum of genre music.
     */
    public GenreMusic obtainMusicalGenre(float temperature) {
        GenreMusic genreMusic;

        if (temperature > 30.0) {
            genreMusic = GenreMusic.HOUSE;
        } else if (temperature >= 15.0 && temperature <= 30.0) {
            genreMusic = GenreMusic.POP;
        } else if (temperature >= 10.0 && temperature <= 15.0) {
            genreMusic = GenreMusic.ROCK;
        } else {
            genreMusic = GenreMusic.CLASSIC;
        }

        return genreMusic;
    }

    private OauthTokenResponse getApiKeyFeign() {
        byte[] bytesEncoded = Base64.encodeBase64((spotifySpecs.getClientId() + ":" + spotifySpecs.getClientSecret()).getBytes());
        String tokenBase64 = new String(bytesEncoded);

        return spotifyTokenClient.getTokenSpotify(HttpConfig.TYPE + "=" + HttpConfig.CLIENT,
                HttpConfig.BASIC_AUTH + tokenBase64);
    }

}
