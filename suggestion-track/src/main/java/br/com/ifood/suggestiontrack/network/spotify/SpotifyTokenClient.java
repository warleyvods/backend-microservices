package br.com.ifood.suggestiontrack.network.spotify;

import br.com.ifood.suggestiontrack.models.spotify.OauthTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Primary
@FeignClient(name = "spotifyTokenClient", url = "${external.spotify.url.token}", fallback = SpotifyTokenClient.SpotifyTokenClientFallback.class)
public interface SpotifyTokenClient {

    /**
     * <p>
     * This method sends a POST using Media Type x-www-form-urlencoded and
     * Header Authorization by passing a base64 "token" password to request a valid spotify token.
     * </p>
     *
     * @param grants String with params to spotify required.
     * @param token  String with ClientId and ClientSecret in Base64 encoded.
     * @return token Bearer Spotify
     */
    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    OauthTokenResponse getTokenSpotify(@RequestBody String grants, @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token);


    @Component
    class SpotifyTokenClientFallback implements SpotifyTokenClient {

        /**
         * Fallback token
         *
         * @param grants String with params to spotify required.
         * @param token  String with ClientId and ClientSecret in Base64 encoded.
         * @return
         */
        @Override
        public OauthTokenResponse getTokenSpotify(String grants, String token) {
            OauthTokenResponse tokenFallback = new OauthTokenResponse();
            tokenFallback.setAccessToken("STRING");
            return tokenFallback;
        }
    }

}
