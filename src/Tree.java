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
        int length = 20, width = 5, facing = 1;
        Color color = Color.WHITE;
        ID id = ID.Branch;
        // root
        xPoints[1] = 250;
        yPoints[1] = 500 - length;
        handler.addObject(new Branch(xPoints, yPoints, color, id));
        branches++;
        branchOut(length, width, facing);
    }

    public void branchOut(int length, int width, int facing) {
        int quadrant = 0;
        int quadrantLimit = 0;
        /*
         * Quadrant 1: Top Right
         * Quadrant 2: Bottom Right
         * Quadrant 3: Top Left
         * Quadrant 4: Bottom Left
         */
        facing = rand.nextInt(360) + 1;
        if (facing > 0 && facing < 91) {
            quadrant = 1;
            quadrantLimit = 90;
        }
        if (facing > 90 && facing < 181) {
            quadrant = 2;
            quadrantLimit = 180;
        }
        if (facing > 180 && facing < 271) {
            quadrant = 3;
            quadrantLimit = 270;
        }
        if (facing > 270 && facing < 361) {
            quadrant = 4;
            quadrantLimit = 360;
        }
        if (branches < 1000) {
            double percentile = facing / quadrantLimit;
            double xTurn = 0, yTurn = 0;
            if ((quadrant == 1 || quadrant == 2) && facing < quadrantLimit / 2)
                xTurn = percentile;
            else if ((quadrant == 1 || quadrant == 2) && facing > quadrantLimit / 2)
                yTurn = percentile;
            else if (percentile == .5) {
                xTurn = percentile;
                yTurn = percentile;
            }
            if (quadrant == 3 && facing < quadrantLimit / 2)
                xTurn = percentile;
            else if (quadrant == 2 && facing > quadrantLimit / 2)
                yTurn = percentile;
            else if (percentile == .5) {
                xTurn = percentile;
                yTurn = percentile;
            }
        }
    }

    public void addBranch(int x, int y, int length, int width, int facing) {
        handler.addObject(null);
    }
}