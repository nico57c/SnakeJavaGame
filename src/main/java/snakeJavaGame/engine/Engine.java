package snakeJavaGame.engine;

import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Stream;

import snakeJavaGame.engine.Config.KEYS;
import snakeJavaGame.engine.EngineCommand.Command;
import snakeJavaGame.engine.Snake.DIR;

public class Engine implements Runnable {

    protected boolean pause = true;
    protected boolean running = true;
    protected boolean gameOver = false;

    protected Config config;
    protected EngineCommand commands;

    protected Snake snake;
    protected Player player;
    protected HighScores highScores;
    protected Position apple;

    protected int boxWidth = 0;
    protected int boxHeight = 0;

    protected DIR currentDir = DIR.RIGHT;

    public Engine(Config config) {
        this.config = config;
        this.boxWidth = Math.round(config.levelWidth() / config.levelBoxesWidth());
        this.boxHeight = Math.round(config.levelHeight() / config.levelBoxesHeight());
    }

    public void init() {

        this.snake = new Snake(config.levelBoxesWidth(), config.levelBoxesHeight());
        this.highScores = new HighScores();
        this.player = Player.defaultPlayer();
        this.apple = new Position((int) (Math.random() * config.levelBoxesWidth()),
                (int) (Math.random() * config.levelBoxesHeight()));

        this.commands = new EngineCommand();

        // Snake directions :
        commands.add(new Command() {
            @Override
            public void execute(KEYS key) {
                if (!isPause()) {
                    DIR dir = DIR.create(key);
                    if (null != dir) {
                        currentDir = dir;
                    }
                }
            }
        }, Config.KEYS.KEY_UP, Config.KEYS.KEY_DOWN, Config.KEYS.KEY_LEFT, Config.KEYS.KEY_RIGHT);

        // Events Pause and Exit :
        commands.add(new Command() {
            @Override
            public void execute(KEYS key) {
                pause = !pause;
            }
        }, Config.KEYS.KEY_PAUSE);

        commands.add(new Command() {
            @Override
            public void execute(KEYS key) {
                running = !running;
            }
        }, Config.KEYS.KEY_EXIT);

        // Player input :
        commands.add(new Command() {
            @Override
            public void execute(KEYS key) {
                if (isPause() || isGameOver()) {
                    ;
                }
            }
        }, Config.KEYS.KEY_UP, Config.KEYS.KEY_DOWN, Config.KEYS.KEY_LEFT, Config.KEYS.KEY_RIGHT);
    }

    public boolean isPause() {
        return pause || !running;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void walk() {
        if (!isGameOver() && !isPause()) {
            if (this.apple.compareTo(snake.head()) == 0) {
                this.snake.runTo(currentDir, true);
                this.generateNewApple();
                this.player.incScore(10);
            } else {
                this.snake.runTo(currentDir, false);
            }
            gameOver = snake.collision();
        }
    }

    private Position generateNewApple() {
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

    public HighScores highScores() {
        return highScores;
    }

    public void exitGame() {
        System.exit(0);
    }

    @Override
    public void run() {
        init();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (running) {
                    walk();
                } else {
                    exitGame();
                }
            }
        }, 0, config.gameSpeed());
    }

    public void input(KEYS name) {
        commands.execute(name);
    }
}
