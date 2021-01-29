package br.com.ifood.suggestiontrack.webrequests;

import br.com.ifood.suggestiontrack.constrants.HTTPConstants;
import feign.Body;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "spotify", url = "https://accounts.spotify.com/api/token")
public interface SpotifyEndpoint {

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @Headers({HttpHeaders.AUTHORIZATION, HTTPConstants.BASIC_AUTHORIZATION + "{tokenBase64}"})
//    @Body(HTTPConstants.GRANT_TYPE + HTTPConstants.CLIENT_CREDENTIALS)
    String getTokenSpotify(@Param("tokenBase64") String tokenBase64);



}
