package snakeJavaGame.engine;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Validator<V> implements Predicate<V> {

    private final List<ValidationRule<V>> rules = new LinkedList<ValidationRule<V>>();

    public Validator(List<ValidationRule<V>> rules) {
        this.rules.addAll(rules);
    }

    @Override
    public  boolean test(V t) {
        return rules.stream().allMatch(rule -> rule.test(t));
    }

}
