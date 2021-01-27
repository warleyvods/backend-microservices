package br.com.ifood.suggestiontrack.models.openweather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {

    @JsonProperty("temp")
    private Float temp;

    @JsonProperty("pressure")
    private String pressure;

    @JsonProperty("humidity")
    private String humidity;

    @JsonProperty("temp_min")
    private String tempMin;

    @JsonProperty("temp_max")
    private String tempMax;

}
