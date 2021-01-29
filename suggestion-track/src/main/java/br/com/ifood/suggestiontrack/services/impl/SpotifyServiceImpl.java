package br.com.ifood.suggestiontrack.services.impl;

import br.com.ifood.suggestiontrack.constrants.HTTPConstants;
import br.com.ifood.suggestiontrack.enums.GenreMusic;
import br.com.ifood.suggestiontrack.models.spotify.AuthenticationToken;
import br.com.ifood.suggestiontrack.models.spotify.Tracks;
import br.com.ifood.suggestiontrack.properties.spotify.SpotifySpecs;
import br.com.ifood.suggestiontrack.services.OpenWeatherService;
import br.com.ifood.suggestiontrack.services.SpotifyService;
import br.com.ifood.suggestiontrack.webrequests.SpotifyBodyDTO;
import br.com.ifood.suggestiontrack.webrequests.SpotifyEndpoint;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SpotifyServiceImpl implements SpotifyService {

    private final RestTemplate restTemplate;
    private final SpotifySpecs spotifySpecs;
    private final SpotifyEndpoint spotifyEndpoint;


    @Override
    public Tracks suggerMusicForGenre(GenreMusic genre) {
        Tracks tracks = null;

        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(spotifySpecs.getUrlRecommendation());
            builder.queryParam("seed_genres", genre);

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add(HttpHeaders.AUTHORIZATION, HTTPConstants.BEARER_AUTHORIZATION + getApiKey());
            HttpEntity<String> entity = new HttpEntity<>(headers);

            tracks = restTemplate.exchange(builder.build().toUriString(), HttpMethod.GET, entity, Tracks.class).getBody();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tracks;
    }

    @Override
    public GenreMusic obtainMusicalGenre(float temperature) {
        GenreMusic genreMusic = null;

        if (temperature > 30.0F) {
            genreMusic = GenreMusic.HOUSE;
        } else if (temperature >= 15.0 && temperature <= 30.0) {
            genreMusic = GenreMusic.POP;
        } else if (temperature >= 10.0 && temperature <= 14.0) {
            genreMusic = GenreMusic.ROCK;
        } else {
            genreMusic = GenreMusic.CLASSIC;
        }

        return genreMusic;
    }

    private String getApiKey() {
        String tokenBase64 = new String(Base64.encodeBase64((spotifySpecs.getClientId() + ":" + spotifySpecs.getClientSecret()).getBytes()));

        System.out.println("BUSCOU NOVO TOKEN");
//        getApiKeyFeign();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, HTTPConstants.BASIC_AUTHORIZATION + tokenBase64);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add(HTTPConstants.GRANT_TYPE, HTTPConstants.CLIENT_CREDENTIALS);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(spotifySpecs.getTokenUrl(), HttpMethod.POST, entity, AuthenticationToken.class).getBody().getAccessToken();
    }

//    private String getApiKeyFeign() {
//        String tokenBase64 = new String(Base64.encodeBase64((spotifySpecs.getClientId() + ":" + spotifySpecs.getClientSecret()).getBytes()));
//
//        String tokenSpotify = spotifyEndpoint.getTokenSpotify(tokenBase64);
//        return tokenSpotify;
//    }


}
