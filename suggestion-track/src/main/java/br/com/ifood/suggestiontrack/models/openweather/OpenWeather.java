package br.com.ifood.suggestiontrack.models.openweather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeather {

    @JsonProperty("main")
    private Main main;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {

        @JsonProperty("temp")
        private Float temp;

    }

}
