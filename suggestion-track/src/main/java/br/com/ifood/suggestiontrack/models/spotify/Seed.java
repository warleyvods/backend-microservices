package br.com.ifood.suggestiontrack.models.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Seed {

    @JsonProperty("initialPoolSize")
    private Integer initialPoolSize;

    @JsonProperty("afterFilteringSize")
    private Integer afterFilteringSize;

    @JsonProperty("afterRelinkingSize")
    private Integer afterRelinkingSize;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("href")
    private String href;

}
