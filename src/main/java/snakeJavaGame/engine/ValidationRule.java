package snakeJavaGame.engine;

import java.util.function.Predicate;

public interface ValidationRule<V> extends Predicate<V> {
    
    @Override
    public boolean test(V t);
}