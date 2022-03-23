import java.util.ArrayList;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.lang.Math;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class Tree extends Canvas implements Runnable {
    private Thread renderThread;
    private Thread objectThread;
    private Handler handler;
    private boolean running;
    private int branches;
    private Keyboard keyboard = new Keyboard();

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
        int width = 2, height = 50, x = 250, y = 500;
        Color color = Color.WHITE;
        ID id = ID.Branch;
        //root
        y -= height;
        handler.addObject(new Branch(250, 500 - height, width, height, color, id));
        branches++;
        branchOut(width, height, x, y, color, id);
    }
    public void branchOut(int width, int height, int x, int y, Color color, ID id) {
        if(branches < 1000) {

        }
    }
}