package br.com.ifood.suggestiontrack.properties.spotify;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class SpotifySpecs {

    private String tokenUrl = "https://accounts.spotify.com/api/token";

    private String clientId = "3aa7f52de8dc4411b30ffd1663b3a0ec";

    private String clientSecret = "ee7e622e704b49cf93aadeae868345fc";

    private String url = "https://api.spotify.com/v1/recommendations";

}
