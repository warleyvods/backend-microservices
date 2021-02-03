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
@FeignClient(name = "spotifyRecommendationClient", url = "${external.spotify.url.recommendation}",
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
            track.add(new Track("Don't Let Me Down (feat. Daya)"));
            track.add(new Track("Havana (feat. Young Thug)"));
            track.add(new Track("Walk It Talk It"));
            track.add(new Track("Let Her Go"));
            track.add(new Track("Burn"));
            track.add(new Track("Sucker for Pain (with Wiz Khalifa, Imagine Dragons, Logic & Ty Dolla $ign feat. X Ambassadors)"));
            track.add(new Track("Mask Off"));
            track.add(new Track("Sex"));
            track.add(new Track("In My Mind"));
            track.add(new Track("All Time Low"));
            track.add(new Track("Elastic Heart"));
            track.add(new Track("Super Far"));
            track.add(new Track("All The Time"));
            track.add(new Track("Ain't It Fun"));
            track.add(new Track("Whatever You Need (feat. Chris Brown & Ty Dolla $ign)"));
            track.add(new Track("Crush"));
            track.add(new Track("goosebumps"));
            track.add(new Track("Please Don't Go"));
            track.add(new Track("Applause"));

            Tracks tracks = new Tracks();
            tracks.setTracksMusic(track);

            return tracks;
        }
    }

}
