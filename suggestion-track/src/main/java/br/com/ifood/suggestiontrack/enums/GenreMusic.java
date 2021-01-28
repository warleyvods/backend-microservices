package br.com.ifood.suggestiontrack.enums;

public enum GenreMusic {

    HOUSE {
        @Override
        public String toString() {
            return "house";
        }
    },
    POP {
        @Override
        public String toString() {
            return "pop";
        }
    },
    ROCK {
        @Override
        public String toString() {
            return "rock";
        }
    },
    CLASSIC {
        @Override
        public String toString() {
            return "classical";
        }
    }

}
