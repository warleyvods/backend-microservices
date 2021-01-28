package br.com.ifood.suggestiontrack.models.spotify.mapper;

import br.com.ifood.suggestiontrack.enums.GenreMusic;
import lombok.Data;

import java.util.List;

@Data
public class TrackName {

    private String city;
    private Float temperature;
    private GenreMusic genre;
    private List<String> name;

}
