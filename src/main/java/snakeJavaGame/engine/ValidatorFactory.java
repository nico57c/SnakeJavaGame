package snakeJavaGame.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import snakeJavaGame.engine.player.validation.rules.PlayerCountryRule;
import snakeJavaGame.engine.player.validation.rules.PlayerNameRule;
import snakeJavaGame.engine.player.validation.rules.PlayerScoreRule;

public class ValidatorFactory {

    public final static List<ValidationRule<Player>> PLAYER_RULES = new ArrayList<ValidationRule<Player>>(
            Arrays.asList(new PlayerCountryRule(), new PlayerNameRule(), new PlayerScoreRule()));

    public static Validator<Player> playerValidator(){
        return new Validator<Player>(PLAYER_RULES);
    }

    public static Validator<Player> playerValidator(List<ValidationRule<Player>> rules){
        return new Validator<Player>(rules);
    }
}
