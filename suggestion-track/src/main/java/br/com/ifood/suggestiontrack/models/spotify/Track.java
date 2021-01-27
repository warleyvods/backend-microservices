package br.com.ifood.suggestiontrack.models.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Track {

    @JsonProperty("album")
    private Album album;

    @JsonProperty("artists")
    private List<Artist> artists = null;

    @JsonProperty("available_markets")
    private List<String> availableMarkets = null;

    @JsonProperty("disc_number")
    private Integer discNumber;

    @JsonProperty("duration_ms")
    private Integer durationMs;

    @JsonProperty("explicit")
    private Boolean explicit;

    @JsonProperty("external_ids")
    private ExternalIds externalIds;

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @JsonProperty("href")
    private String href;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("popularity")
    private Integer popularity;

    @JsonProperty("preview_url")
    private String previewUrl;

    @JsonProperty("track_number")
    private Integer trackNumber;

    @JsonProperty("type")
    private String type;

    @JsonProperty("uri")
    private String uri;

}
