package snakeJavaGame.engine;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

import snakeJavaGame.engine.Config.KEYS;

public class Snake {

    private Deque<Position> parts;
    private final int maxX;
    private final int maxY;

    public static enum DIR {
        LEFT, RIGHT, UP, DOWN;

        public static DIR create(KEYS key) {
            switch (key) {
                case KEY_DOWN:
                    return DIR.DOWN;
                case KEY_UP:
                    return DIR.UP;
                case KEY_LEFT:
                    return DIR.LEFT;
                case KEY_RIGHT:
                    return DIR.RIGHT;
                case KEY_EXIT:
                case KEY_PAUSE:
                case KEY_VALID:
                default:
                    return null;
            }
        }
    };

    public Snake(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        parts = new ConcurrentLinkedDeque<Position>();
        parts.add(new Position(maxX / 2, maxY / 2));
    }

    public Position head() {
        return parts.getFirst();
    }

    public List<Position> parts() {
        List<Position> res = new ArrayList<Position>();
        parts.forEach(position -> res.add(position));
        return res;
    }

    /**
     * Return removed position if is needed
     * 
     * @return
     */
    public void runTo(DIR dir, boolean grow) {

        switch (dir) {
            case LEFT:
                parts.addFirst(head().relativePosition(-1, 0, maxX, maxY));
            break;
            case RIGHT:
                parts.addFirst(head().relativePosition(1, 0, maxX, maxY));
            break;
            case UP:
                parts.addFirst(head().relativePosition(0, -1, maxX, maxY));
            break;
            case DOWN:
                parts.addFirst(head().relativePosition(0, 1, maxX, maxY));
            break;
        }

        if (!grow)
            parts.removeLast();
    }

    public boolean collision() {
        return parts.stream().filter(position -> {
            return head().compareTo(position) == 0;
        }).count() > 1;
    }
}
