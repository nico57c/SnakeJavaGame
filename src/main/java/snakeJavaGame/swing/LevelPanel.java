package snakeJavaGame.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import snakeJavaGame.engine.Config;
import snakeJavaGame.engine.Engine;
import snakeJavaGame.engine.Snake.DIR;

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
        engine.startWalk();

        registerKeyboardAction(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pause");
                engine.pause();
            }
        }, Engine.EVENT_PAUSE, KeyStroke.getKeyStroke(config.keyPause()), WHEN_FOCUSED);

        registerKeyboardAction(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Down");
                if (!engine.isPause())
                    engine.snakeDirection(DIR.DOWN);
            }
        }, Engine.EVENT_DOWN, KeyStroke.getKeyStroke(config.keyDown()), WHEN_FOCUSED);

        registerKeyboardAction(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Up");
                if (!engine.isPause())
                    engine.snakeDirection(DIR.UP);
            }
        }, Engine.EVENT_UP, KeyStroke.getKeyStroke(config.keyUp()), WHEN_FOCUSED);

        registerKeyboardAction(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Left");
                if (!engine.isPause())
                    engine.snakeDirection(DIR.LEFT);
            }
        }, Engine.EVENT_LEFT, KeyStroke.getKeyStroke(config.keyLeft()), WHEN_FOCUSED);

        registerKeyboardAction(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Right");
                if (!engine.isPause())
                    engine.snakeDirection(DIR.RIGHT);
            }
        }, Engine.EVENT_RIGHT, KeyStroke.getKeyStroke(config.keyRight()), WHEN_FOCUSED);

        registerKeyboardAction(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit");
                engine.exit();
            }
        }, Engine.EVENT_EXIT, KeyStroke.getKeyStroke(config.keyExit()), WHEN_FOCUSED);
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
