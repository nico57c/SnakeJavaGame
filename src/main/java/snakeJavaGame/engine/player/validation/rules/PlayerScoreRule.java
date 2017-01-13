package snakeJavaGame.engine.player.validation.rules;

import snakeJavaGame.engine.Player;
import snakeJavaGame.engine.ValidationRule;

public class PlayerScoreRule implements ValidationRule<Player> {

    @Override
    public boolean test(Player model) {
        return model.getScore()>=0;
    }

}
