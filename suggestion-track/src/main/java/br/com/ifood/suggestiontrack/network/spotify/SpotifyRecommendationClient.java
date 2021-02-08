package br.com.ifood.suggestiontrack.network.spotify;

import br.com.ifood.suggestiontrack.models.spotify.Music;
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

            ArrayList<Music> music = new ArrayList<>();
            music.add(new Music("Don't Let Me Down (feat. Daya)"));
            music.add(new Music("Havana (feat. Young Thug)"));
            music.add(new Music("Walk It Talk It"));
            music.add(new Music("Let Her Go"));
            music.add(new Music("Burn"));
            music.add(new Music("Sucker for Pain (with Wiz Khalifa, Imagine Dragons, Logic & Ty Dolla $ign feat. X Ambassadors)"));
            music.add(new Music("Mask Off"));
            music.add(new Music("Sex"));
            music.add(new Music("In My Mind"));
            music.add(new Music("All Time Low"));
            music.add(new Music("Elastic Heart"));
            music.add(new Music("Super Far"));
            music.add(new Music("All The Time"));
            music.add(new Music("Ain't It Fun"));
            music.add(new Music("Whatever You Need (feat. Chris Brown & Ty Dolla $ign)"));
            music.add(new Music("Crush"));
            music.add(new Music("goosebumps"));
            music.add(new Music("Please Don't Go"));
            music.add(new Music("Applause"));

            Tracks tracks = new Tracks();
            tracks.setTracksMusic(music);

            return tracks;
        }
    }

}
