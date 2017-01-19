package snakeJavaGame.engine.player.validation.rules;

import java.io.UnsupportedEncodingException;

import snakeJavaGame.engine.Player;
import snakeJavaGame.engine.ValidationRule;
import snakeJavaGame.engine.player.Letters.LETTER;

public class PlayerNameRule implements ValidationRule<Player> {

    @Override
    public boolean test(Player model) {
        try {
            return model.getName().getBytes("US-ASCII").length > 0 && model.getCountry().matches(LETTER.regex());
        } catch (UnsupportedEncodingException e) {
            System.out.println("ERROR: US-ASCII for player name!");
            return false;
        }
    }

}