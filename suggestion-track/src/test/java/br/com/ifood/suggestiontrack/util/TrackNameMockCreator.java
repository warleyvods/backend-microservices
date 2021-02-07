package br.com.ifood.suggestiontrack.util;

import br.com.ifood.suggestiontrack.enums.GenreMusic;
import br.com.ifood.suggestiontrack.models.spotify.mapper.TrackName;

import java.util.ArrayList;

public class TrackNameMockCreator {


    public static TrackName createTrackNameResponse() {
        TrackName trackName = new TrackName();
        trackName.setName(new ArrayList<>());
        trackName.setCity("Goiania");
        trackName.setTemperature(25F);
        trackName.setGenre(GenreMusic.POP);

        return trackName;
    }




}
