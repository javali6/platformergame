package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class GamePanel extends JPanel {

    private int x = 100, y = 100;

    private BufferedImage bufferedImage;
    private float xDelta;
    private float yDelta;

    public GamePanel() {

        MouseInputs mouseInputs = new MouseInputs(this);
        importImage();

        setPanelSize();

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }

    private void importImage() {

        InputStream inputStream = getClass().getResourceAsStream("/player_sprites.png");
        try {
            assert inputStream != null;
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        BufferedImage subImage = bufferedImage.getSubimage(1*64, 8*40, 64, 40);
        graphics.drawImage(subImage, (int) xDelta, (int) yDelta, 128, 80, null );
    }

    public void changeXDelta(int value) {
        this.xDelta += value;

    }
    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void setRecPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }


}
