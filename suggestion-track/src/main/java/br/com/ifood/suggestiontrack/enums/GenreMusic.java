package br.com.ifood.suggestiontrack.enums;

public enum GenreMusic {

    HOUSE("house"),
    POP("pop"),
    ROCK("rock"),
    CLASSIC("classic");

    public final String label;

    GenreMusic(String label) {
        this.label = label;
    }

}
