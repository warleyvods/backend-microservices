package br.com.ifood.suggestiontrack.models.spotify.mapper;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TrackName {

    @Schema(description = "Name of city", example = "Dubai")
    private String city;

    @Schema(description = "Temperature of city or place", example = "29.0")
    private Float temperature;

    @Schema(description = "Genre music based on temperature", example = "pop")
    private String genre;

    @Schema(description = "List with 20 music names based on genre music",
            example = "Nigo in Beverly Hills - Well Done Fever...")
    private List<String> musicNames;

}
