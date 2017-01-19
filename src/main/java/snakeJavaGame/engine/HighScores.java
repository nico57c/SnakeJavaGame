package snakeJavaGame.engine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HighScores {

    private List<Player> scores;
    private Map<Integer, Player> ranking = new HashMap<Integer, Player>();

    public HighScores(List<Player> scores) {
        this.scores = scores;
        ranking();
    }

    public HighScores() {
        this.scores = new ArrayList<Player>();
        ranking();
    }

    public Map<Integer, Player> ranking() {
        Comparator<Player> compareScore = Comparator.comparing(Player::getScore).reversed()
                .thenComparing(Comparator.comparing(Player::getCountry))
                .thenComparing(Comparator.comparing(Player::getName))
                .thenComparing(Comparator.comparing(Player::getGenre));
        ranking.clear();
        this.scores.stream().sorted(compareScore).forEachOrdered(player -> {
            ranking.put(ranking.size() + 1, player);
        });
        return ranking;
    }

    public Map<Integer, Player> getRanking() {
        return ranking;
    }

    public void addPlayer(Player player) throws ValidationException {
        if (ValidatorFactory.playerValidator().test(player)) {
            this.scores.add(player);
            this.ranking();
        } else {
            throw new ValidationException("Player is not valid.");
        }
    }

    public int rank(Player player) {
        return this.ranking.entrySet().stream().filter(entry -> {
            return player.equals(entry.getValue());
        }).max(Entry.comparingByKey()).orElse(null).getKey();
    }

    public String toString() {
        return this.ranking.entrySet().stream().map(entry -> entry.getKey() + "\t\t" + entry.getValue().toString())
                .reduce("", (a, b) -> a + "\n" + b);
    }
}
