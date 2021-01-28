package br.com.ifood.suggestiontrack.services;

import br.com.ifood.suggestiontrack.models.spotify.Tracks;

public interface SuggestService {

    Tracks suggerMusicByTemperatureCity(String city);

}
