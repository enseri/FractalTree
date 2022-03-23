import java.awt.Color;
import java.awt.Graphics;

public class Branch extends Object{
    public Branch(int x, int y, int width, int height, Color color, ID id){
        super(x, y, width, height, color, id);
    }

    public void tick(){

    }


    public void render(Graphics g) {
        g.setColor(super.getColor());
        g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }
}