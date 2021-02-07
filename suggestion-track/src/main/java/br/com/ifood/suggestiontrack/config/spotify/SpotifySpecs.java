package br.com.ifood.suggestiontrack.config.spotify;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class loads the variables responsible for configuring the api call for the Spotify
 * using Spring Cloud Config.
 * @author Warley Vinicius
 */
@Data
@Component
public class SpotifySpecs {

    @Value("${external.spotify.url.token}")
    private String tokenUrl;

    @Value("${external.spotify.client.clientId}")
    private String clientId;

    @Value("${external.spotify.client.clientSecret}")
    private String clientSecret;

    @Value(("${external.spotify.url.recommendation}"))
    private String urlRecommendation;

}
