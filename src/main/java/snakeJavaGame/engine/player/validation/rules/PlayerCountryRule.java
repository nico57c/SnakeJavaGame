package snakeJavaGame.engine.player.validation.rules;

import snakeJavaGame.engine.Player;
import snakeJavaGame.engine.ValidationRule;

public class PlayerCountryRule implements ValidationRule<Player> {

    @Override
    public boolean test(Player model) {
        return model.getCountry().length() > 0 && model.getCountry().length() < 4;
    }

}