package snakeJavaGame.engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String name;
    protected String country;
    protected int score = 0;

    public static enum Genre {
        F("FEMALE"), M("MALE");

        Genre(String value) {
            this.value = value;
        }

        private final String value;

        public String value() {
            return value;
        }

        public boolean equals(String val) {
            return value.equals(val) || value.toString().equals(val);
        }

        public static boolean contains(String val) {
            return new ArrayList<Genre>(Arrays.asList(Genre.values())).stream().anyMatch(item -> {
                return item.value.equals(val) || item.toString().equals(val);
            });
        }

        public static Genre valueOfString(String val) {
            return new ArrayList<Genre>(Arrays.asList(Genre.values())).stream().filter(item -> {
                return item.value.equals(val) || item.toString().equals(val);
            }).findFirst().orElse(null);
        }
    }

    protected Genre genre;

    public Player(String name, String country, Genre genre) {
        this.name = name.trim();
        this.country = country.trim();
        this.genre = genre;
    }

    public Player(String name, String country, Genre genre, int score) {
        this.name = name.trim().toUpperCase();
        this.country = country.trim().toUpperCase();
        this.genre = genre;
        this.score = score;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getGenreStr() {
        return genre.value;
    }

    public String getCountry() {
        return country;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String toString() {
        return getName() + "\t" + getCountry() + "/" + getGenre() + "\t\t" + getScore();
    }
}
