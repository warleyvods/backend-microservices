package br.com.ifood.suggestiontrack.models.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Album {

    @JsonProperty("album_type")
    private String albumType;

    @JsonProperty("artists")
    private List<Artist> artists = null;

    @JsonProperty("available_markets")
    private List<String> availableMarkets = null;

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @JsonProperty("href")
    private String href;

    @JsonProperty("id")
    private String id;

    @JsonProperty("images")
    private List<Image> images = null;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("uri")
    private String uri;


}
