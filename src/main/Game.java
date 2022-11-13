package main;

public class Game implements Runnable{
    private final int FPS_SET = 120;

    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Thread gameThread;

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (true) {

            long now = System.nanoTime();
            if(now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
                frames++;
            }

            if(System.currentTimeMillis() - lastCheck > 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }

        }

    }
}
