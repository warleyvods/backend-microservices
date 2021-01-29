package br.com.ifood.suggestiontrack.webrequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SpotifyBodyDTO {

    private String grant_type;

}
