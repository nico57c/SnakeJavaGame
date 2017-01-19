package snakeJavaGame.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import snakeJavaGame.engine.Config.KEYS;

public class EngineCommand {

    @FunctionalInterface
    public interface Command {
        public void execute(KEYS key);
    }

    private Map<KEYS, List<Command>> commands;

    public EngineCommand() {
        commands = new HashMap<KEYS, List<Command>>();
    }

    public void add(Command command, KEYS name) {
        commands.computeIfAbsent(name, (k) -> new ArrayList<Command>()).add(command);
    }

    public void add(Command command, KEYS... names) {
        for (KEYS name : names) {
            commands.computeIfAbsent(name, (k) -> new ArrayList<Command>()).add(command);
        }

    }

    public void execute(KEYS name) {
        if (commands.containsKey(name)) {
            commands.get(name).parallelStream().forEach(item -> item.execute(name));
        }
    }
}
