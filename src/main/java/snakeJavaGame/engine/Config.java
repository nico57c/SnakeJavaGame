package snakeJavaGame.engine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Properties;

public class Config {

    private Path file;

    private Properties properties;

    private static final String WINDOW_WIDTH = "WINDOW_WIDTH";
    private static final String WINDOW_HEIGHT = "WINDOW_HEIGHT";
    private static final String LEVEL_WIDTH = "LEVEL_WIDTH";
    private static final String LEVEL_HEIGHT = "LEVEL_HEIGHT";
    private static final String LEVEL_BOXES_WIDTH = "BOXES_WIDTH";
    private static final String LEVEL_BOXES_HEIGHT = "BOXES_HEIGHT";
    private static final String LEVEL_BOXES_POS = "BOXES_POS";

    private static final String KEY_DOWN = "K_DOWN";
    private static final String KEY_UP = "K_UP";
    private static final String KEY_LEFT = "K_LEFT";
    private static final String KEY_RIGHT = "K_RIGHT";
    private static final String KEY_PAUSE = "K_PAUSE";
    private static final String KEY_EXIT = "K_EXIT";

    private static final String GAME_SPEED = "GAME_SPEED";

    public Config() {
        ;
    }

    public boolean load(Path file) throws IOException {
        this.file = file;
        if (this.file.toFile().exists()) {
            return load(Files.newInputStream(file));
        } else {
            return false;
        }
    }

    public boolean load(InputStream input) throws IOException {
        this.properties = new Properties();
        this.properties.load(input);

        return this.properties.keySet().containsAll(Arrays.asList(WINDOW_WIDTH, WINDOW_HEIGHT, LEVEL_WIDTH,
                LEVEL_HEIGHT, LEVEL_BOXES_WIDTH, LEVEL_BOXES_HEIGHT, LEVEL_BOXES_POS, GAME_SPEED));
    }

    public int windowWidth() {
        return Integer.valueOf(properties.getProperty(WINDOW_WIDTH));
    }

    public int windowHeight() {
        return Integer.valueOf(properties.getProperty(WINDOW_HEIGHT));
    }

    public int levelWidth() {
        return Integer.valueOf(properties.getProperty(LEVEL_WIDTH));
    }

    public int levelHeight() {
        return Integer.valueOf(properties.getProperty(LEVEL_HEIGHT));
    }

    public int levelBoxesWidth() {
        return Integer.valueOf(properties.getProperty(LEVEL_BOXES_WIDTH));
    }

    public int levelBoxesHeight() {
        return Integer.valueOf(properties.getProperty(LEVEL_BOXES_HEIGHT));
    }

    public Position[] levelBoxes() {
        String positions = properties.getProperty(LEVEL_BOXES_POS);
        return Position.valuesOf(positions);
    }

    public String keyUp() {
        return properties.getProperty(KEY_UP);
    }

    public String keyDown() {
        return properties.getProperty(KEY_DOWN);
    }

    public String keyLeft() {
        return properties.getProperty(KEY_LEFT);
    }

    public String keyRight() {
        return properties.getProperty(KEY_RIGHT);
    }

    public String keyPause() {
        return properties.getProperty(KEY_PAUSE);
    }

    public String keyExit() {
        return properties.getProperty(KEY_EXIT);
    }

    public int gameSpeed() {
        return Integer.valueOf(properties.getProperty(GAME_SPEED));
    }

}
