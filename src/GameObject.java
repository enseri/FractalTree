import java.awt.Graphics;
import java.awt.Color;

public abstract class GameObject {

    protected int x = 0, y = 0, width = 0, height = 0;
    protected ID id;
    protected Color color;

    public GameObject(int x, int y, int width, int height, Color color, ID id) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.id = id;
    }

    public void setColor(Color colorSelected) {
        color = colorSelected;
    }

    public Color getColor() {
        return color;
    }

    public abstract void tick();

    public abstract void render(Graphics g);


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

}