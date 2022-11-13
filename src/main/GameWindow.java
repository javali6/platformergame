package main;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow(GamePanel gamePanel) throws HeadlessException {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(gamePanel);
        this.setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true);
    }
}
