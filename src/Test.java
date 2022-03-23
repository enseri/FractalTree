import java.awt.Color;
import java.awt.Graphics;

public class Test extends Object{
    public Test(int x, int y, int width, int height, Color color, ID id){
        super(x, y, width, height, color, id);
    }

    public void tick(){

    }


    public void render(Graphics g) {
        g.setColor(super.getColor());
        int[] xPoints = new int[]{20, 40}, yPoints = new int[]{20, 40};
        g.drawPolygon(xPoints, yPoints, 2);
        g.fillPolygon(xPoints, yPoints, 2);
    }
}