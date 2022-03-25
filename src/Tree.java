import java.util.ArrayList;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.lang.Math;
import java.util.concurrent.TimeUnit;

import java.util.Scanner;
import java.util.Random;

public class Tree extends Canvas implements Runnable {
    private Thread renderThread;
    private Thread objectThread;
    private Handler handler;
    private boolean running;
    private int branches;
    private Keyboard keyboard = new Keyboard();
    Random rand = new Random();

    public Tree() {
        new Window(500, 500, "Platformer", this);
        handler = new Handler();
        this.addKeyListener(keyboard);
        handler.addObject(new Background(0, 0, 500, 500, Color.BLACK, ID.Background));
        begin();
    }

    public synchronized void start() {
        renderThread = new Thread(this);
        objectThread = new Thread(this);
        renderThread.start();
        objectThread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            renderThread.join();
            objectThread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
        stop();
    }

    public void tick() {
        handler.tick();
    }

    public void render() {
        boolean error = false;
        BufferStrategy bs = null;
        do {
            try {
                bs = this.getBufferStrategy();
            } catch (NullPointerException E) {
                error = true;
            }
        } while (error);
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.green);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        handler.render(g);
        g.dispose();
        do {
            try {
                bs.show();
            } catch (NullPointerException E) {
                error = true;
            }
        } while (error);
    }

    public static void main(String[] args) throws Exception {
        new Tree();
    }

    public void begin() {
        branches = 0;
        int[] xPoints = new int[] { 250, 0 }, yPoints = new int[] { 500, 0 };
        int length = 100, width = 5;
        Color color = Color.WHITE;
        ID id = ID.Branch;
        // root
        xPoints[1] = 250;
        yPoints[1] = 500 - length;
        handler.addObject(new Branch(xPoints, yPoints, color, id));
        branches++;
        branchOut(30, length, xPoints, yPoints, color, id);
    }
    public void branchOut(int facing, int length, int[] xPointsOrigin, int[] yPointsOrigin, Color color, ID id) {
        length -= .25 * length;
        double xTurn = .25, yTurn = .75;
        if (length > 5) {
            int[] xPoints = new int[2];
            int[] yPoints = new int[2];
            xPoints[0] = xPointsOrigin[1];
            yPoints[0] = yPointsOrigin[1];
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException E) {

            }
            // right diagonal
            if (xPoints[0] + (int) (.50 * length) > -1 && xPoints[0] + (int) (.50 * length) < 500
                    && yPoints[0] - (int) (.50 * length) > -1 && yPoints[0] - (int) (.50 * length) < 500) {
                xPoints[1] = xPoints[0] + (int) (xTurn * length);
                yPoints[1] = yPoints[0] - (int) (yTurn * length);
                addBranch(xPoints, yPoints, color, id);
                branchOut(facing, length, xPoints, yPoints, color, id);
            }
            // left diagonal
            if (xPoints[0] - (int) (.50 * length) > -1 && xPoints[0] - (int) (.50 * length) < 500
                    && yPoints[0] - (int) (.50 * length) > -1 && yPoints[0] - (int) (.50 * length) < 500) {
                xPoints[1] = xPoints[0] - (int) (xTurn * length);
                yPoints[1] = yPoints[0] - (int) (yTurn * length);
                addBranch(xPoints, yPoints, color, id);
                branchOut(facing, length, xPoints, yPoints, color, id);
            }
        }
    }

    public void addBranch(int[] xPoints, int[] yPoints, Color color, ID id) {
        branches++;
        handler.addObject(new Branch(xPoints, yPoints, color, id));
    }
}