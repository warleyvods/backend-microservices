package br.com.ifood.suggestiontrack.services;

import br.com.ifood.suggestiontrack.enums.GenreMusic;
import br.com.ifood.suggestiontrack.models.spotify.Tracks;
import org.springframework.stereotype.Service;


public interface SpotifyService {

    Tracks suggerMusicForGenre(GenreMusic genreMusic);

    GenreMusic obtainMusicalGenre(float temperature);

}
