package br.com.ifood.suggestiontrack.models.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Image {

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("url")
    private String url;

    @JsonProperty("width")
    private Integer width;

}
