package br.com.ifood.suggestiontrack.models.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Tracks {

    @JsonProperty("tracks")
    private List<Music> tracksMusic;

}
