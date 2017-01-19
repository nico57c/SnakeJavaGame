package snakeJavaGame.engine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Config {

    private Path file;

    private Properties properties;

    public enum CONFIGS {
        WINDOW_WIDTH("WINDOW_WIDTH"), WINDOW_HEIGHT("WINDOW_HEIGHT"), LEVEL_WIDTH("LEVEL_WIDTH"), LEVEL_HEIGHT(
                "LEVEL_HEIGHT"), LEVEL_BOXES_WIDTH("BOXES_WIDTH"), LEVEL_BOXES_HEIGHT("BOXES_HEIGHT"), LEVEL_BOXES_POS(
                        "BOXES_POS"), GAME_SPEED("GAME_SPEED");

        private String value;

        CONFIGS(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        };

        public static List<String> getValues() {
            return new ArrayList<CONFIGS>(Arrays.asList(CONFIGS.values())).stream().map(CONFIGS::getValue)
                    .collect(Collectors.toList());
        }

    }

    public enum KEYS {
        KEY_DOWN("K_DOWN"), KEY_UP("K_UP"), KEY_LEFT("K_LEFT"), KEY_RIGHT("K_RIGHT"), KEY_PAUSE("K_PAUSE"), KEY_EXIT(
                "K_EXIT"), KEY_VALID("K_VALID");

        private String value;

        KEYS(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        };

        public static List<String> getValues() {
            return new ArrayList<KEYS>(Arrays.asList(KEYS.values())).stream().map(KEYS::getValue)
                    .collect(Collectors.toList());
        }

    };

    public Config() {
        ;
    }

    public boolean load(Path file) throws IOException, InterruptedException {
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

        List<String> keys = CONFIGS.getValues();
        keys.addAll(KEYS.getValues());

        if (!this.properties.keySet().containsAll(keys)) {

            System.out.println("Config keys are not presents : ");
            keys.stream().filter(item -> !properties.containsKey(item)).forEach(System.out::println);

            System.out.println("Config keys are unknown : ");
            properties.keySet().stream().filter(item -> !keys.contains(item)).forEach(System.out::println);

            return false;
        } else {
            return true;
        }
    }

    public int windowWidth() {
        return Integer.valueOf(properties.getProperty(CONFIGS.WINDOW_WIDTH.getValue()));
    }

    public int windowHeight() {
        return Integer.valueOf(properties.getProperty(CONFIGS.WINDOW_HEIGHT.getValue()));
    }

    public int levelWidth() {
        return Integer.valueOf(properties.getProperty(CONFIGS.LEVEL_WIDTH.getValue()));
    }

    public int levelHeight() {
        return Integer.valueOf(properties.getProperty(CONFIGS.LEVEL_HEIGHT.getValue()));
    }

    public int levelBoxesWidth() {
        return Integer.valueOf(properties.getProperty(CONFIGS.LEVEL_BOXES_WIDTH.getValue()));
    }

    public int levelBoxesHeight() {
        return Integer.valueOf(properties.getProperty(CONFIGS.LEVEL_BOXES_HEIGHT.getValue()));
    }

    public Position[] levelBoxes() {
        String positions = properties.getProperty(CONFIGS.LEVEL_BOXES_POS.getValue());
        return Position.valuesOf(positions);
    }

    public int gameSpeed() {
        return Integer.valueOf(properties.getProperty(CONFIGS.GAME_SPEED.getValue()));
    }

    public String keyUp() {
        return properties.getProperty(KEYS.KEY_UP.getValue());
    }

    public String keyDown() {
        return properties.getProperty(KEYS.KEY_DOWN.getValue());
    }

    public String keyLeft() {
        return properties.getProperty(KEYS.KEY_LEFT.getValue());
    }

    public String keyRight() {
        return properties.getProperty(KEYS.KEY_RIGHT.getValue());
    }

    public String keyPause() {
        return properties.getProperty(KEYS.KEY_PAUSE.getValue());
    }

    public String keyExit() {
        return properties.getProperty(KEYS.KEY_EXIT.getValue());
    }

    public String keyValid() {
        return properties.getProperty(KEYS.KEY_VALID.getValue());
    }

}
