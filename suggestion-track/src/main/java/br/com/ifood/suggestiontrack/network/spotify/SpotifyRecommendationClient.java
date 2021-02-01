package br.com.ifood.suggestiontrack.network.spotify;

import br.com.ifood.suggestiontrack.models.spotify.Track;
import br.com.ifood.suggestiontrack.models.spotify.Tracks;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Primary
@FeignClient(name = "spotifyRecommendationClient", url = "https://api.spotify.com/v1/recommendations",
        fallback = SpotifyRecommendationClient.SpotifyRecommendationClientFallback.class)
public interface SpotifyRecommendationClient {

    /**
     * Method to suggest music based on genre indicated by temperature city search.
     *
     * @param seedGenres required param to spotify (Ex: "pop" mandatory lowercase)
     * @param oAuth      String token to send in Header request
     * @return object Tracks with 20 name musics based on genre.
     */
    @GetMapping
    Tracks suggestMusicForGenre(@RequestParam("seed_genres") String seedGenres,
                                @RequestHeader(value = HttpHeaders.AUTHORIZATION) String oAuth);

    @Component
    class SpotifyRecommendationClientFallback implements SpotifyRecommendationClient {

        /**
         * Fallback method when api return error or high latency
         *
         * @param seedGenres required param to spotify (Ex: "pop" mandatory lowercase)
         * @param oAuth      String token to send in Header request
         * @return object Tracks with 20 name music "STATICS" based on random with default.
         */
        @Override
        public Tracks suggestMusicForGenre(String seedGenres, String oAuth) {

            ArrayList<Track> track = new ArrayList<>();
            track.add(new Track("Musica 1"));
            track.add(new Track("Musica 2"));
            track.add(new Track("Musica 3"));
            track.add(new Track("Musica 4"));
            track.add(new Track("Musica 5"));
            track.add(new Track("Musica 6"));
            track.add(new Track("Musica 7"));
            track.add(new Track("Musica 8"));
            track.add(new Track("Musica 9"));
            track.add(new Track("Musica 11"));
            track.add(new Track("Musica 12"));
            track.add(new Track("Musica 13"));
            track.add(new Track("Musica 14"));
            track.add(new Track("Musica 15"));
            track.add(new Track("Musica 16"));
            track.add(new Track("Musica 17"));
            track.add(new Track("Musica 18"));
            track.add(new Track("Musica 19"));
            track.add(new Track("Musica 20"));

            Tracks tracks = new Tracks();
            tracks.setTracksMusic(track);

            return tracks;
        }
    }

}
