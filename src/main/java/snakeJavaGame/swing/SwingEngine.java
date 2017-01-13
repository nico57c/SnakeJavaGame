package snakeJavaGame.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import snakeJavaGame.engine.Config;
import snakeJavaGame.engine.Engine;

public class SwingEngine extends Engine {

    public SwingEngine(Config config) {
        super(config);
    }

    public void renderPlatform(Graphics graph) {
        graph.setColor(Color.CYAN);
        graph.fillRect(apple().getX(), apple().getY(), this.boxWidth, this.boxHeight);
    }

    public void renderSnake(Graphics graph) {
        graph.setColor(Color.RED);
        snakePartsPosition().forEach(position -> {
            graph.fillRect(position.getX(), position.getY(), this.boxWidth, this.boxHeight);
        });
    }

    public void renderPause(Graphics graph) {
        if (isPause()) {
            graph.setColor(Color.RED);
            graph.drawString("PAUSE  (p to start)", 20, 20);
        } else {
            Rectangle2D bounds = graph.getFontMetrics().getStringBounds("PAUSE  (p to start)", graph);
            graph.clearRect(20, 10, (int) bounds.getWidth(), (int) bounds.getHeight());
        }
    }

    public void renderGameOver(Graphics graph) {
        if (gameOver()) {
            graph.setColor(Color.RED);
            graph.drawString("GAME OVER!  (escape to exit)", config.levelBoxesWidth() / 2,
                    config.levelBoxesHeight() / 2);
        }
    }
}
