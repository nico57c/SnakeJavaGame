package snakeJavaGame.engine.player.validation.rules;

import java.io.UnsupportedEncodingException;

import snakeJavaGame.engine.Player;
import snakeJavaGame.engine.ValidationRule;

public class PlayerNameRule implements ValidationRule<Player> {

    @Override
    public boolean test(Player model) {
        try {
            return model.getName().getBytes("US-ASCII").length>0;
        } catch (UnsupportedEncodingException e) {
            System.out.println("ERROR: US-ASCII for player name!");
            return false;
        }
    }

}