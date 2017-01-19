package snakeJavaGame.swing;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import snakeJavaGame.engine.Config;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;

    public Main() {

        Config config = new Config();
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.ini");
            if (!config.load(input)) {
                System.out.println("Config file load error!");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        SwingEngine engine = new SwingEngine(config);
        JPanel background = new JPanel();
        background.setLocation(0, 0);
        background.setOpaque(false);
        LevelPanel levelPanel = new LevelPanel(config, engine, background);

        add(levelPanel);
        add(background);

        setResizable(false);
        setLocation(0, 0);
        setSize(config.windowWidth(), config.windowHeight());
        getContentPane().setPreferredSize(new Dimension(config.windowWidth(), config.windowHeight()));
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame ex;
                ex = new Main();
                ex.setVisible(true);
            }
        });
    }
}