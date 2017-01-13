package snakeJavaGame.engine;

import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Stream;

import snakeJavaGame.engine.Snake.DIR;

public class Engine {

    protected boolean pause = true;
    protected boolean running = true;
    protected boolean gameOver = false;

    protected Snake snake;
    protected Position apple;
    protected Config config;

    protected int boxWidth = 0;
    protected int boxHeight = 0;

    public static final String EVENT_PAUSE = "pause";
    public static final String EVENT_UP = "up";
    public static final String EVENT_DOWN = "down";
    public static final String EVENT_LEFT = "left";
    public static final String EVENT_RIGHT = "right";
    public static final String EVENT_EXIT = "exit";

    protected DIR currentDir = DIR.RIGHT;

    public Engine(Config config) {
        this.config = config;

        this.boxWidth = Math.round(config.levelWidth() / config.levelBoxesWidth());
        this.boxHeight = Math.round(config.levelHeight() / config.levelBoxesHeight());
        this.snake = new Snake(config.levelBoxesWidth(), config.levelBoxesHeight());
        this.apple = new Position((int) (Math.random() * config.levelBoxesWidth()),
                (int) (Math.random() * config.levelBoxesHeight()));
    }

    public void pause() {
        pause = !pause;
    }

    public boolean isPause() {
        return pause;
    }

    public void exit() {
        this.running = !running;
        System.exit(1);
    }

    public void snakeDirection(DIR dir) {
        this.currentDir = dir;
    }

    public boolean gameOver() {
        return gameOver;
    }

    public void walk() {
        if (!gameOver) {
            if (this.apple.compareTo(snake.head()) == 0) {
                this.snake.runTo(currentDir, true);
                this.generateNewApple();
            } else {
                this.snake.runTo(currentDir, false);
            }
            gameOver = snake.collision();
        }
    }

    public void startWalk() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!isPause()) {
                    walk();
                }
            }
        }, 0, config.gameSpeed());
    }

    public Position generateNewApple() {
        do {
            this.apple = new Position((int) (Math.random() * config.levelBoxesWidth()),
                    (int) (Math.random() * config.levelBoxesHeight()));
        } while (snake.parts().contains(apple));
        return this.apple;
    }

    public Stream<Position> snakePartsPosition() {
        return snake.parts().stream().map(position -> {
            return new Position(boxWidth * position.getX(), boxHeight * position.getY());
        });
    }

    public Position apple() {
        return new Position(apple.getX() * boxWidth, apple.getY() * boxHeight);
    }
}
