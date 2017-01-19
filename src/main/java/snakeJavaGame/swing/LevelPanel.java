package snakeJavaGame.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import snakeJavaGame.engine.Config;
import snakeJavaGame.engine.Config.KEYS;

public class LevelPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    protected Config config;
    protected SwingEngine engine;
    protected JPanel parent;

    public LevelPanel(Config config, SwingEngine engine, JPanel parent) {
        super();

        this.parent = parent;
        parent.setBackground(Color.LIGHT_GRAY);

        setSize(config.levelWidth(), config.levelHeight());
        setBackground(Color.BLACK);
        setOpaque(false);
        setLocation((config.windowWidth() - config.levelWidth()) / 2,
                (config.windowHeight() - config.levelHeight()) / 2);

        this.config = config;
        this.engine = engine;

        registerKeyboardAction(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pause");
                engine.input(KEYS.KEY_PAUSE);
            }
        }, KEYS.KEY_PAUSE.getValue(), KeyStroke.getKeyStroke(config.keyPause()), WHEN_FOCUSED);

        registerKeyboardAction(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Down");
                engine.input(KEYS.KEY_DOWN);
            }
        }, KEYS.KEY_DOWN.getValue(), KeyStroke.getKeyStroke(config.keyDown()), WHEN_FOCUSED);

        registerKeyboardAction(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Up");
                engine.input(KEYS.KEY_UP);
            }
        }, KEYS.KEY_UP.getValue(), KeyStroke.getKeyStroke(config.keyUp()), WHEN_FOCUSED);

        registerKeyboardAction(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Left");
                engine.input(KEYS.KEY_LEFT);
            }
        }, KEYS.KEY_LEFT.getValue(), KeyStroke.getKeyStroke(config.keyLeft()), WHEN_FOCUSED);

        registerKeyboardAction(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Right");
                engine.input(KEYS.KEY_RIGHT);
            }
        }, KEYS.KEY_RIGHT.getValue(), KeyStroke.getKeyStroke(config.keyRight()), WHEN_FOCUSED);

        registerKeyboardAction(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit");
                engine.input(KEYS.KEY_EXIT);
            }
        }, KEYS.KEY_EXIT.getValue(), KeyStroke.getKeyStroke(config.keyExit()), WHEN_FOCUSED);

        registerKeyboardAction(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Valid");
                engine.input(KEYS.KEY_VALID);
            }
        }, KEYS.KEY_VALID.getValue(), KeyStroke.getKeyStroke(config.keyValid()), WHEN_FOCUSED);

        // START GAME :
        engine.run();
    }

    @Override
    public void paintComponent(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getSize().width, getSize().height);

        engine.renderPlatform(g);
        engine.renderSnake(g);

        Graphics cg = parent.getGraphics();

        engine.renderPause(cg);
        engine.renderGameOver(cg);
        cg.dispose();
        parent.paintComponents(cg);

        g.dispose();
        repaint();
    }
}
