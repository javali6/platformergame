package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Utils.Constants.PlayerConstants.*;
import static Utils.Constants.Directions.*;


public class GamePanel extends JPanel {

    private int x = 100, y = 100;

    private BufferedImage bufferedImage;
    private float xDelta;
    private float yDelta;
    private BufferedImage[][] animations;
    private int aniTick;
    private int aniIndex;
    private final int aniSpeed = 20;


    private int playerAction = JUMPING;
    private int playerDirection = UP;

    private boolean moving = false;

    public GamePanel() {

        MouseInputs mouseInputs = new MouseInputs(this);
        importImage();
        loadAnimations();

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

    private void loadAnimations() {
        animations = new BufferedImage[9][6];
        for(int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = bufferedImage.getSubimage(i * 64, j*40, 64, 40);
            }
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        updateAnimationTick();
        setAnimation();
        updatePosition();
        graphics.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int) yDelta, 256, 160, null );
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }

    private void updatePosition() {
        if(moving) {
            switch (playerDirection) {
                case LEFT -> {xDelta -= 4;}
                case UP -> {yDelta -= 4;}
                case DOWN -> {yDelta += 4;}
                case RIGHT -> {xDelta += 4;}
            }
        }

    }

    private void setAnimation() {
        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
    }

    public void setDirection(int direction) {
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }




}
