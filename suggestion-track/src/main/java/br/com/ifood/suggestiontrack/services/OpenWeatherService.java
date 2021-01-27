package br.com.ifood.suggestiontrack.services;

import br.com.ifood.suggestiontrack.constrants.HTTPConstants;
import br.com.ifood.suggestiontrack.models.spotify.AuthenticationToken;
import br.com.ifood.suggestiontrack.models.spotify.Tracks;
import br.com.ifood.suggestiontrack.properties.openwheather.OpenWeatherEspecs;
import br.com.ifood.suggestiontrack.models.openweather.OpenWeather;
import br.com.ifood.suggestiontrack.properties.spotify.SpotifySpecs;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * @author Warley Vinicius
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OpenWeatherService {

    private final OpenWeatherEspecs openWeatherEspecs;
    private final SpotifySpecs spotifySpecs;

    private final RestTemplate restTemplate;

    public Float getTemperatureByCity(String cidade) {
        Float temperatura = null;

        try {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(openWeatherEspecs.getUrl());
            uriComponentsBuilder.queryParam("q", cidade);
            uriComponentsBuilder.queryParam("appid", openWeatherEspecs.getAppId());
            uriComponentsBuilder.queryParam("units", openWeatherEspecs.getUnits());

            OpenWeather OpenWeatherObjMap = restTemplate.getForObject(uriComponentsBuilder.build().toUriString(), OpenWeather.class);

            temperatura = Objects.requireNonNull(OpenWeatherObjMap).getMain().getTemp();


            System.out.println(suggestTracksForGenre("POP"));


        } catch (HttpClientErrorException ex) {
            ex.getStackTrace();
        }
        return temperatura;
    }


    private String getApiKey() {
        String encodedToken = new String(Base64.encodeBase64((spotifySpecs.getClientId() + ":" + spotifySpecs.getClientSecret()).getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, HTTPConstants.BASIC_AUTHORIZATION + encodedToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add(HTTPConstants.GRANT_TYPE, HTTPConstants.CLIENT_CREDENTIALS);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        String accessToken = restTemplate.exchange(spotifySpecs.getTokenUrl(), HttpMethod.POST, entity, AuthenticationToken.class).getBody().getAccessToken();

        return accessToken;
    }



    public Tracks suggestTracksForGenre(String genre) {
        Tracks tracks = null;
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(spotifySpecs.getUrl());
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




}
