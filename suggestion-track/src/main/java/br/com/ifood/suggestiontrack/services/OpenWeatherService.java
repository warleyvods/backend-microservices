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
public interface OpenWeatherService {

    Float getTemperatureByCity(String city);

}
